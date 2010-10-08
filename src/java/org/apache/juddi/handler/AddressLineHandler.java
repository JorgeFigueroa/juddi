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
package org.apache.juddi.handler;

import org.apache.juddi.datatype.AddressLine;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.util.xml.XMLUtils;
import org.w3c.dom.Element;

/**
 * "Knows about the creation and populating of AddressLine objects.
 * Returns AddressLine."
 *
 * @author Steve Viens (sviens@apache.org)
 */
public class AddressLineHandler extends AbstractHandler
{
  public static final String TAG_NAME = "addressLine";

  private HandlerMaker maker = null;

  protected AddressLineHandler(HandlerMaker maker)
  {
    this.maker = maker;
  }

  public RegistryObject unmarshal(Element element)
  {
    AddressLine obj = new AddressLine();

    // Attributes
    String keyName = element.getAttribute("keyName");
    if ((keyName != null) && (keyName.trim().length() > 0))
      obj.setKeyName(keyName);

    String keyValue = element.getAttribute("keyValue");
    if ((keyValue != null) && (keyValue.trim().length() > 0))
      obj.setKeyValue(keyValue);

    // Text Node Value
    obj.setLineValue(XMLUtils.getText(element));

    // Child Elements
    // {none}

    return obj;
  }

  public void marshal(RegistryObject object,Element parent)
  {
    AddressLine line = (AddressLine)object;
    Element element = parent.getOwnerDocument().createElement(TAG_NAME);

    String keyName = line.getKeyName();
    if ((keyName != null) && (keyName.trim().length() > 0))
      element.setAttribute("keyName",keyName);

    String keyValue = line.getKeyValue();
    if ((keyValue != null) && (keyValue.trim().length() > 0))
      element.setAttribute("keyValue",keyValue);

    String lineValue = line.getLineValue();
    if (lineValue != null)
      element.appendChild(parent.getOwnerDocument().createTextNode(lineValue));

    parent.appendChild(element);
  }


  /***************************************************************************/
  /***************************** TEST DRIVER *********************************/
  /***************************************************************************/


  public static void main(String args[])
    throws Exception
  {
    // create the source object
    AddressLine lineIn = new AddressLine("AddressLine1","keyNameAttr","keyValueAttr");

    // unmarshal & marshal (object->xml->object)
    HandlerMaker maker = HandlerMaker.getInstance();
    AbstractHandler handler = maker.lookup(AddressLineHandler.TAG_NAME);
    Element element = null;
    handler.marshal(lineIn,element);
    AddressLine lineOut = (AddressLine)handler.unmarshal(element);

    // compare unmarshaled with marshaled obj
    if (lineOut.equals(lineIn))
      System.out.println("Input and output are the same.");
    else
      System.out.println("Input and output are NOT the same!");
  }
}