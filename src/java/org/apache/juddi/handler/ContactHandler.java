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

import org.apache.juddi.datatype.Address;
import org.apache.juddi.datatype.AddressLine;
import org.apache.juddi.datatype.Description;
import org.apache.juddi.datatype.Email;
import org.apache.juddi.datatype.PersonName;
import org.apache.juddi.datatype.Phone;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.datatype.business.Contact;
import org.apache.juddi.util.xml.XMLUtils;
import org.w3c.dom.Element;

/**
 * ContactHandler
 *
 * "Knows about the creation and populating of KeyedReference objects.
 * Returns Contact."
 *
 * @author Steve Viens (sviens@apache.org)
 * @author Anou Mana (anou_mana@users.sourceforge.net)
 */
public class ContactHandler extends AbstractHandler
{
  public static final String TAG_NAME = "contact";

  private HandlerMaker maker = null;

  protected ContactHandler(HandlerMaker maker)
  {
    this.maker = maker;
  }

  public RegistryObject unmarshal(Element element)
  {
    Contact obj = new Contact();
    Vector nodeList = null;
    AbstractHandler handler = null;

    // Attributes
    String useType = element.getAttribute("useType");
    if ((useType != null) && (useType.trim().length() > 0))
      obj.setUseType(useType);

    // Text Node Value
    // {none}

    // Child Elements
    nodeList = XMLUtils.getChildElementsByTagName(element,PersonNameHandler.TAG_NAME);
    if (nodeList.size() > 0)
    {
      handler = maker.lookup(PersonNameHandler.TAG_NAME);
      obj.setPersonName((PersonName)handler.unmarshal((Element)nodeList.elementAt(0)));
    }

    nodeList = XMLUtils.getChildElementsByTagName(element,DescriptionHandler.TAG_NAME);
    for (int i=0; i<nodeList.size(); i++)
    {
      handler = maker.lookup(DescriptionHandler.TAG_NAME);
      obj.addDescription((Description)handler.unmarshal((Element)nodeList.elementAt(i)));
    }

    nodeList = XMLUtils.getChildElementsByTagName(element,AddressHandler.TAG_NAME);
    for (int i=0; i<nodeList.size(); i++)
    {
      handler = maker.lookup(AddressHandler.TAG_NAME);
      obj.addAddress((Address)handler.unmarshal((Element)nodeList.elementAt(i)));
    }

    nodeList = XMLUtils.getChildElementsByTagName(element,EmailHandler.TAG_NAME);
    for (int i=0; i<nodeList.size(); i++)
    {
      handler = maker.lookup(EmailHandler.TAG_NAME);
      obj.addEmail((Email)handler.unmarshal((Element)nodeList.elementAt(i)));
    }

    nodeList = XMLUtils.getChildElementsByTagName(element,PhoneHandler.TAG_NAME);
    for (int i=0; i<nodeList.size(); i++)
    {
      handler = maker.lookup(PhoneHandler.TAG_NAME);
      obj.addPhone((Phone)handler.unmarshal((Element)nodeList.elementAt(i)));
    }

    return obj;
  }

  public void marshal(RegistryObject object,Element parent)
  {
    Contact contact = (Contact)object;
    Element element = parent.getOwnerDocument().createElement(TAG_NAME);
    AbstractHandler handler = null;

    String useType = contact.getUseType();
    if ((useType != null) && (useType.trim().length() > 0))
      element.setAttribute("useType",useType);

    Vector descrVector = contact.getDescriptionVector();
    if ((descrVector!=null) && (descrVector.size() > 0))
    {
      handler = maker.lookup(DescriptionHandler.TAG_NAME);
      for (int i=0; i < descrVector.size(); i++)
        handler.marshal((Description)descrVector.elementAt(i),element);
    }

    PersonName personName = contact.getPersonName();
    if ((personName != null))
    {
      handler = maker.lookup(PersonNameHandler.TAG_NAME);
      handler.marshal(personName,element);
    }

    Vector phoneVector = contact.getPhoneVector();
    if ((phoneVector!=null) && (phoneVector.size() > 0))
    {
      handler = maker.lookup(PhoneHandler.TAG_NAME);
      for (int i=0; i < phoneVector.size(); i++)
        handler.marshal((Phone)phoneVector.elementAt(i),element);
    }

    Vector emailVector = contact.getEmailVector();
    if ((emailVector!=null) && (emailVector.size() > 0))
    {
      handler = maker.lookup(EmailHandler.TAG_NAME);
      for (int i=0; i < emailVector.size(); i++)
        handler.marshal((Email)emailVector.elementAt(i),element);
    }

    Vector addressVector = contact.getAddressVector();
    if ((addressVector!=null) && (addressVector.size() > 0))
    {
      handler = maker.lookup(AddressHandler.TAG_NAME);
      for (int i=0; i < addressVector.size(); i++)
        handler.marshal((Address)addressVector.elementAt(i),element);
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
    AbstractHandler handler = maker.lookup(ContactHandler.TAG_NAME);
    Element parent = XMLUtils.newRootElement();
    Element child = null;

    Address address = new Address();
    address.setUseType("myAddressUseType");
    address.setSortCode("sortThis");
    address.setTModelKey(null);
    address.addAddressLine(new AddressLine("AddressLine1","keyNameAttr","keyValueAttr"));
    address.addAddressLine(new AddressLine("AddressLine2"));

    Contact contact = new Contact();
    //contact.setUseType("myAddressUseType");
    contact.setPersonNameValue("Bob Whatever");
    contact.addDescription(new Description("Bob is a big fat jerk"));
    contact.addDescription(new Description("obBay sIay a igBay atFay erkJay","es"));
    contact.addEmail(new Email("bob@whatever.com"));
    contact.addPhone(new Phone("(603)559-1901"));
    contact.addAddress(address);

    System.out.println();

    RegistryObject regObject = contact;
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

    System.out.println();
  }
}