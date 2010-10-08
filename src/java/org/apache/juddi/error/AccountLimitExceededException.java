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
package org.apache.juddi.error;

import org.apache.juddi.datatype.response.Result;

/**
 * Thrown to indicate that a UDDI Exception was encountered.
 *
 * @author Steve Viens (sviens@apache.org)
 */
public class AccountLimitExceededException extends RegistryException
{
  public AccountLimitExceededException(String msg)
  {
    super(Result.E_ACCOUNT_LIMIT_EXCEEDED_CODE+": "+msg);

    // grab the locale specific error
    // message from the ResourceBundle

    String errMsg = Result.E_ACCOUNT_LIMIT_EXCEEDED_MSG;

    Result result = new Result(
      Result.E_ACCOUNT_LIMIT_EXCEEDED,
      Result.E_ACCOUNT_LIMIT_EXCEEDED_CODE,
      errMsg);

    this.setFaultActor("");
    this.setFaultCode("Client");
    this.setFaultString("Client Error");
    this.addResult(result);
  }
}