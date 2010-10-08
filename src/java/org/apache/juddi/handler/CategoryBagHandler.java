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

import java.util.Vector;

import org.apache.juddi.datatype.CategoryBag;
import org.apache.juddi.datatype.KeyedReference;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.util.xml.XMLUtils;
import org.w3c.dom.Element;

/**
 * "Knows about the creation and populating of CategoryBag objects.
 * Returns CategoryBag."
 *
 * @author Steve Viens (sviens@apache.org)
 */
public class CategoryBagHandler extends AbstractHandler
{
  public static final String TAG_NAME = "categoryBag";

  private HandlerMaker maker = null;

  protected CategoryBagHandler(HandlerMaker maker)
  {
    this.maker = maker;
  }

  public RegistryObject unmarshal(Element element)
  {
    CategoryBag obj = new CategoryBag();
    Vector nodeList = null;
    AbstractHandler handler = null;

    // Attributes
    // {none}

    // Text Node Value
    // {none}

    // Child Elements
    nodeList = XMLUtils.getChildElementsByTagName(element,KeyedReferenceHandler.TAG_NAME);
    for (int i=0; i<nodeList.size(); i++)
    {
      handler = maker.lookup(KeyedReferenceHandler.TAG_NAME);
      obj.addKeyedReference((KeyedReference)handler.unmarshal((Element)nodeList.elementAt(i)));
    }

    return obj;
  }

  public void marshal(RegistryObject object,Element parent)
  {
    CategoryBag categoryBag = (CategoryBag)object;
    Element element = parent.getOwnerDocument().createElement(TAG_NAME);

    Vector keyedRefVector = categoryBag.getKeyedReferenceVector();
    if ((keyedRefVector!=null) && (keyedRefVector.size() > 0))
    {
      AbstractHandler handler = maker.lookup(KeyedReferenceHandler.TAG_NAME);
      for (int i=0; i < keyedRefVector.size(); i++)
        handler.marshal((KeyedReference)keyedRefVector.elementAt(i),element);
    }

    parent.appendChild(element);
  }


  /***************************************************************************/
  /***************************** TEST DRIVER *********************************/
  /***************************************************************************/


  public static void main(String args[])
    throws Exception
  {

    HandlerMaker maker = HandlerMaker.getInstance();
    AbstractHandler handler = maker.lookup(CategoryBagHandler.TAG_NAME);

    Element parent = XMLUtils.newRootElement();
    Element child = null;

    CategoryBag categoryBag = new CategoryBag();
    categoryBag.addKeyedReference(new KeyedReference("catBagKeyName","catBagKeyValue"));
    categoryBag.addKeyedReference(new KeyedReference("uuid:8ff45356-acde-4a4c-86bf-f953611d20c6","catBagKeyName2","catBagKeyValue2"));

    System.out.println();

    RegistryObject regObject = categoryBag;
    handler.marshal(regObject,parent);
    child = (Element)parent.getFirstChild();
    parent.removeChild(child);
    XMLUtils.writeXML(child,System.out);

    System.out.println();

    regObject = handler.unmarshal(child);
    handler.marshal(regObject,parent);
    child = (Element)parent.getFirstChild();
    parent.removeChild(child);
    XMLUtils.writeXML(child,System.out);
  }
}