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

import org.apache.juddi.datatype.Phone;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.util.xml.XMLUtils;
import org.w3c.dom.Element;

/**
 * "Knows about the creation and populating of Phone objects.
 * Returns Phone."
 *
 * @author Steve Viens (sviens@apache.org)
 */
public class PhoneHandler extends AbstractHandler
{
  public static final String TAG_NAME = "phone";

  private HandlerMaker maker = null;

  protected PhoneHandler(HandlerMaker maker)
  {
    this.maker = maker;
  }

  public RegistryObject unmarshal(Element element)
  {
    Phone obj = new Phone();

    // Attributes
    String useType = element.getAttribute("useType");
    if ((useType != null) && (useType.trim().length() > 0))
      obj.setUseType(useType);

    // Text Node Value
    obj.setValue(XMLUtils.getText(element));

    // Child Elements
    // {none}

    return obj;
  }

  public void marshal(RegistryObject object,Element parent)
  {
    Phone phone = (Phone)object;
    Element element = parent.getOwnerDocument().createElementNS(null,TAG_NAME);

    String useType = phone.getUseType();
    if ((useType != null) && (useType.trim().length() > 0))
      element.setAttribute("useType",useType);

    String phoneValue = phone.getValue();
    if (phoneValue != null)
      element.appendChild(parent.getOwnerDocument().createTextNode(phoneValue));

    parent.appendChild(element);
  }
}