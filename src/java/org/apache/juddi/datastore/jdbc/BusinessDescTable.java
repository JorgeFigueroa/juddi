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
import org.apache.juddi.datatype.Description;
import org.apache.juddi.datatype.business.BusinessEntity;
import org.apache.juddi.util.Config;
import org.apache.juddi.util.jdbc.ConnectionManager;
import org.apache.juddi.util.jdbc.Transaction;
import org.apache.juddi.uuidgen.UUIDGen;
import org.apache.juddi.uuidgen.UUIDGenFactory;

/**
 * @author Steve Viens (sviens@apache.org)
 */
class BusinessDescTable
{
  // private reference to the jUDDI logger
  private static Log log = LogFactory.getLog(BusinessDescTable.class);

  static String insertSQL = null;
  static String selectSQL = null;
  static String deleteSQL = null;

  static {
    // buffer used to build SQL statements
    StringBuffer sql = null;

    // build insertSQL
    sql = new StringBuffer(150);
    sql.append("INSERT INTO BUSINESS_DESCR (");
    sql.append("BUSINESS_KEY,");
    sql.append("BUSINESS_DESCR_ID,");
    sql.append("LANG_CODE,");
    sql.append("DESCR) ");
    sql.append("VALUES (?,?,?,?)");
    insertSQL = sql.toString();

    // build selectSQL
    sql = new StringBuffer(200);
    sql.append("SELECT ");
    sql.append("LANG_CODE,");
    sql.append("DESCR, ");
    sql.append("BUSINESS_DESCR_ID ");
    sql.append("FROM BUSINESS_DESCR ");
    sql.append("WHERE BUSINESS_KEY=? ");
    sql.append("ORDER BY BUSINESS_DESCR_ID");
    selectSQL = sql.toString();

    // build deleteSQL
    sql = new StringBuffer(100);
    sql.append("DELETE FROM BUSINESS_DESCR ");
    sql.append("WHERE BUSINESS_KEY=?");
    deleteSQL = sql.toString();
  }

  /**
   * Insert new row into the BUSINESS_DESCR table.
   *
   * @param  businessKey BusinessKey to the BusinessEntity object that owns the Description to be inserted
   * @param  descList Vector of Description objects holding values to be inserted
   * @param  connection JDBC connection
   * @throws java.sql.SQLException
   */
  public static void insert(
    String businessKey,
    Vector descList,
    Connection connection)
    throws java.sql.SQLException
  {
    if ((descList == null) || (descList.size() == 0))
      return; // everything is valid but no elements to insert

    PreparedStatement statement = null;

    try
    {
      statement = connection.prepareStatement(insertSQL);
      statement.setString(1, businessKey.toString());

      int listSize = descList.size();
      for (int descID = 0; descID < listSize; descID++)
      {
        Description desc = (Description) descList.elementAt(descID);

        statement.setInt(2, descID);
        statement.setString(3, desc.getLanguageCode());
        statement.setString(4, desc.getValue());

        log.debug(
          "insert into BUSINESS_DESCR table:\n\n\t"
            + insertSQL
            + "\n\t BUSINESS_KEY="
            + businessKey.toString()
            + "\n\t BUSINESS_DESCR_ID="
            + descID
            + "\n\t LANG_CODE="
            + desc.getLanguageCode()
            + "\n\t DESCR="
            + desc.getValue()
            + "\n");

        statement.executeUpdate();
      }
    }
    finally
    {
      try
      {
        statement.close();
      }
      catch (Exception e)
      { /* ignored */
      }
    }
  }

  /**
   * Select all rows from the BUSINESS_DESCR table for a given BusinessKey.
   *
   * @param  businessKey BusinessKey
   * @param  connection JDBC connection
   * @throws java.sql.SQLException
   */
  public static Vector select(String businessKey, Connection connection)
    throws java.sql.SQLException
  {
    Vector descList = new Vector();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try
    {
      // create a statement to query with
      statement = connection.prepareStatement(selectSQL);
      statement.setString(1, businessKey.toString());

      log.debug(
        "select from BUSINESS_DESCR table:\n\n\t"
          + selectSQL
          + "\n\t BUSINESS_KEY="
          + businessKey.toString()
          + "\n");

      // execute the statement
      resultSet = statement.executeQuery();

      Description desc = null;
      while (resultSet.next())
      {
        desc = new Description();
        desc.setLanguageCode(resultSet.getString(1));//("LANG_CODE"));
        desc.setValue(resultSet.getString(2));//("DESCR"));
        descList.add(desc);
      }

      return descList;
    }
    finally
    {
      try
      {
        resultSet.close();
        statement.close();
      }
      catch (Exception e)
      { /* ignored */
      }
    }
  }

  /**
   * Delete multiple rows from the BUSINESS_DESCR table that are assigned to the
   * BusinessKey specified.
   *
   * @param  businessKey BusinessKey
   * @param  connection JDBC connection
   * @throws java.sql.SQLException
   */
  public static void delete(String businessKey, Connection connection)
    throws java.sql.SQLException
  {
    PreparedStatement statement = null;

    try
    {
      // prepare the delete
      statement = connection.prepareStatement(deleteSQL);
      statement.setString(1, businessKey.toString());

      log.debug(
        "delete from BUSINESS_DESCR table:\n\n\t"
          + deleteSQL
          + "\n\t BUSINESS_KEY="
          + businessKey.toString()
          + "\n");

      // execute
      statement.executeUpdate();
    }
    finally
    {
      try
      {
        statement.close();
      }
      catch (Exception e)
      { /* ignored */
      }
    }
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

  public static void test(Connection connection) throws Exception
  {
    Transaction txn = new Transaction();
    UUIDGen uuidgen = UUIDGenFactory.getUUIDGen();

    if (connection != null)
    {
      try
      {
        String businessKey = uuidgen.uuidgen();
        BusinessEntity business = new BusinessEntity();
        business.setBusinessKey(businessKey);
        business.setAuthorizedName("sviens");
        business.setOperator("WebServiceRegistry.com");

        Vector descList = new Vector();
        descList.add(new Description("blah, blah, blah", "en"));
        descList.add(new Description("Yadda, Yadda, Yadda", "it"));
        descList.add(new Description("WhoobWhoobWhoobWhoob", "cy"));
        descList.add(new Description("Haachachachacha", "km"));

        String authorizedUserID = "sviens";

        // begin a new transaction
        txn.begin(connection);

        // insert a new BusinessEntity
        BusinessEntityTable.insert(business, authorizedUserID, connection);

        // insert a Collection of Description objects
        BusinessDescTable.insert(businessKey, descList, connection);

        // select the Collection of Description objects
        descList = BusinessDescTable.select(businessKey, connection);

        //  // delete the Collection of Description objects
        //  BusinessDescTable.delete(businessKey,connection);

        // re-select the Collection of Description objects
        descList = BusinessDescTable.select(businessKey, connection);

        // commit the transaction
        txn.commit();
      }
      catch (Exception ex)
      {
        try
        {
          txn.rollback();
        }
        catch (java.sql.SQLException sqlex)
        {
          sqlex.printStackTrace();
        }
        throw ex;
      }
    }
  }
}