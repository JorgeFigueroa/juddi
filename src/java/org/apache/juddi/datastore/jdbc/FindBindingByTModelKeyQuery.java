/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.juddi.datastore.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.datatype.TModelBag;
import org.apache.juddi.datatype.request.FindQualifiers;
import org.apache.juddi.util.Config;
import org.apache.juddi.util.jdbc.ConnectionManager;
import org.apache.juddi.util.jdbc.DynamicQuery;
import org.apache.juddi.util.jdbc.Transaction;

/**
 * @author Steve Viens (sviens@apache.org)
 */
class FindBindingByTModelKeyQuery
{
  // private reference to the jUDDI logger
  private static Log log = LogFactory.getLog(FindBindingByTModelKeyQuery.class);

  static String selectSQL;
  static
  {
    // build selectSQL
    StringBuffer sql = new StringBuffer(200);
    sql.append("SELECT T.BINDING_KEY,T.LAST_UPDATE ");
    sql.append("FROM BINDING_TEMPLATE T,TMODEL_INSTANCE_INFO I ");
    selectSQL = sql.toString();
  }

  /**
   * Select ...
   *
   * @param connection JDBC connection
   * @throws java.sql.SQLException
   */
  public static Vector select(String serviceKey,TModelBag tModelBag,Vector keysIn,FindQualifiers qualifiers,Connection connection)
    throws java.sql.SQLException
  {
    // if there is a keysIn vector but it doesn't contain
    // any keys then the previous query has exhausted
    // all possibilities of a match so skip this call.
    if ((keysIn != null) && (keysIn.size() == 0))
      return keysIn;

    Vector keysOut = new Vector();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    // construct the SQL statement
    DynamicQuery sql = new DynamicQuery(selectSQL);
    appendWhere(sql,serviceKey,tModelBag,qualifiers);
    appendIn(sql,keysIn);
    appendOrderBy(sql,qualifiers);

    try
    {
      log.debug(sql.toString());
      
      statement = sql.buildPreparedStatement(connection);
      resultSet = statement.executeQuery();

      while (resultSet.next())
        keysOut.addElement(resultSet.getString(1));//("BINDING_KEY"));

      return keysOut;
    }
    finally
    {
      try {
        resultSet.close();
      }
      catch (Exception e)
      {
        log.warn("An Exception was encountered while attempting to close " +
          "the Find BindingTemplate ResultSet: "+e.getMessage(),e);
      }

      try {
        statement.close();
      }
      catch (Exception e)
      {
        log.warn("An Exception was encountered while attempting to close " +
          "the Find BindingTemplate Statement: "+e.getMessage(),e);
      }
    }
  }

  /**
   *
   */
  private static void appendWhere(DynamicQuery sql,String serviceKey,TModelBag tModelBag,FindQualifiers qualifiers)
  {
    sql.append("WHERE I.BINDING_KEY = T.BINDING_KEY ");
    sql.append("AND T.SERVICE_KEY = ? ");
    sql.addValue(serviceKey);

    Vector keyVector = tModelBag.getTModelKeyVector();

    int vectorSize = keyVector.size();
    if (vectorSize > 0)
    {
      sql.append("AND (");

      for (int i=0; i<vectorSize; i++)
      {
        String key = (String)keyVector.elementAt(i);

        sql.append("I.TMODEL_KEY = ? ");
        sql.addValue(key);

        if (i+1 < vectorSize)
          sql.append(" OR ");
      }

      sql.append(") ");
    }
  }

  /**
   * Utility method used to construct SQL "IN" statements such as
   * the following SQL example:
   *
   *   SELECT * FROM TABLE WHERE MONTH IN ('jan','feb','mar')
   *
   * @param sql StringBuffer to append the final results to
   * @param keysIn Vector of Strings used to construct the "IN" clause
   */
  private static void appendIn(DynamicQuery sql,Vector keysIn)
  {
    if (keysIn == null)
      return;

    sql.append("AND T.BINDING_KEY IN (");

    int keyCount = keysIn.size();
    for (int i=0; i<keyCount; i++)
    {
      String key = (String)keysIn.elementAt(i);
      sql.append("?");
      sql.addValue(key);
      
      if ((i+1) < keyCount)
        sql.append(",");
    }

    sql.append(") ");
  }

  /**
   *
   */
  private static void appendOrderBy(DynamicQuery sql,FindQualifiers qualifiers)
  {
    sql.append("ORDER BY ");

    if (qualifiers == null)
      sql.append("T.LAST_UPDATE DESC");
    else if (qualifiers.sortByDateAsc)
      sql.append("T.LAST_UPDATE ASC");
    else
      sql.append("T.LAST_UPDATE DESC");
  }


  /***************************************************************************/
  /***************************** TEST DRIVER *********************************/
  /***************************************************************************/


  public static void main(String[] args)
    throws Exception
  {
    // make sure we're using a DBCP DataSource and
    // not trying to use JNDI to aquire one.
    Config.setStringProperty("juddi.useConnectionPool","true");

    Connection conn = null;
    try {
      conn = ConnectionManager.aquireConnection();
      test(conn);
    }
    finally {
      if (conn != null)
        conn.close();
    }
  }

  public static void test(Connection connection)
    throws Exception
  {
    String serviceKey = "13411e97-24cf-43d1-bee0-455e7ec5e9fc";

    TModelBag tModelBag = new TModelBag();
    Vector tModelKeyVector = new Vector();
    tModelKeyVector.addElement("2a33d7d7-2b73-4de9-99cd-d4c51c186bce");
    tModelKeyVector.addElement("2a33d7d7-2b73-4de9-99cd-d4c51c186bce");
    tModelBag.setTModelKeyVector(tModelKeyVector);

    Vector keysIn = new Vector();
    keysIn.add("13411e97-24cf-43d1-bee0-455e7ec5e9fc");
    keysIn.add("3f244f19-7ba7-4c3e-a93e-ae33e530794b");
    keysIn.add("3009f336-98c1-4193-a22f-fea73e79c909");
    keysIn.add("3ef4772f-e04b-46ed-8065-c5a4e167b5ba");

    Transaction txn = new Transaction();

    if (connection != null)
    {
      try
      {
        // begin a new transaction
        txn.begin(connection);

        select(serviceKey,tModelBag,keysIn,null,connection);
        select(serviceKey,tModelBag,null,null,connection);

        // commit the transaction
        txn.commit();
      }
      catch(Exception ex)
      {
        try { txn.rollback(); }
        catch(java.sql.SQLException sqlex) { sqlex.printStackTrace(); }
        throw ex;
      }
    }
  }
}