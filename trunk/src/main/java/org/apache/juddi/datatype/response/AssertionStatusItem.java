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
package org.apache.juddi.datatype.response;

import org.apache.juddi.datatype.KeyedReference;
import org.apache.juddi.datatype.RegistryObject;

/**
 * @author Steve Viens (sviens@apache.org)
 */
public class AssertionStatusItem implements RegistryObject
{
  CompletionStatus completionStatus;
  String fromKey;
  String toKey;
  KeyedReference keyedRef;
  KeysOwned keysOwned;

  /**
   * default constructor
   */
  public AssertionStatusItem()
  {
  }

  /**
   *
   */
  public void setCompletionStatus(CompletionStatus status)
  {
    this.completionStatus = status;
  }

  /**
   *
   */
  public void setCompletionStatus(String status)
  {
    if (status != null)
      this.completionStatus = new CompletionStatus(status);
    else
      this.completionStatus = null;
  }

  /**
   *
   */
  public CompletionStatus getCompletionStatus()
  {
    return this.completionStatus;
  }

  /**
   *
   */
  public void setFromKey(String keyValue)
  {
    this.fromKey = keyValue;
  }

  /**
   *
   */
  public String getFromKey()
  {
    return this.fromKey;
  }

  /**
   *
   */
  public void setToKey(String keyValue)
  {
    this.toKey = keyValue;
  }

  /**
   *
   */
  public String getToKey()
  {
    return this.toKey;
  }

  /**
   *
   */
  public void setKeyedReference(KeyedReference keyedRef)
  {
    this.keyedRef = keyedRef;
  }

  /**
   *
   */
  public KeyedReference getKeyedReference()
  {
    return this.keyedRef;
  }

  /**
   *
   */
  public void setKeysOwned(KeysOwned keys)
  {
    this.keysOwned = keys;
  }

  /**
   *
   */
  public KeysOwned getKeysOwned()
  {
    return this.keysOwned;
  }
}