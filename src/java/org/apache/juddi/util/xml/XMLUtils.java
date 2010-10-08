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
package org.apache.juddi.util.xml;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Steve Viens (sviens@apache.org)
 */
public class XMLUtils
{
  // jUDDI XML document builder
  private static DocumentBuilder docBuilder = null;

  /**
   *
   * @param element
   * @return String
   */
  public static String getText(Element element)
  {
    StringBuffer textBuffer = new StringBuffer();

    NodeList nodeList = element.getChildNodes();
    for (int i=0; i<nodeList.getLength(); i++)
    {
      if (nodeList.item(i).getNodeType() == Element.TEXT_NODE)
        textBuffer.append(nodeList.item(i).getNodeValue());
    }

    return textBuffer.toString().trim();
  }

  /**
   *
   * @param element
   * @param tagName
   * @return Vector
   */
  public static Vector getChildElementsByTagName(Element element,String tagName)
  {
    Vector result = new Vector();

    NodeList children = element.getChildNodes();
    for (int i=0; i<children.getLength(); i++)
    {
      Node node = children.item(i);
      
      //System.out.println("node name:       "+node.getNodeName());
      //System.out.println("node local name: "+node.getLocalName());
      
      if (node.getNodeType() == Node.ELEMENT_NODE && node.getLocalName().equals(tagName))
        result.addElement(node); // matching element
    }

    return result;
  }

  /**
   * create a new empty xml element
   * @return a new org.w3c.Element named "root"
   */
  public static Element newRootElement()
  {
    Element element = null;

    try
    {
      javax.xml.parsers.DocumentBuilderFactory factory =
        javax.xml.parsers.DocumentBuilderFactory.newInstance();

      javax.xml.parsers.DocumentBuilder builder =
        factory.newDocumentBuilder();

      Document document = builder.newDocument();
      Element holder = document.createElement("root");
      document.appendChild(holder);
      element = document.getDocumentElement();
    }
    catch(Exception ex) { ex.printStackTrace(); }

    return element;
  }

  public static Document createDocument()
  {
    if (docBuilder == null)
      docBuilder = createDocumentBuilder();

    return docBuilder.newDocument();
  }

  private static DocumentBuilder createDocumentBuilder()
  {
    if (docBuilder != null)
      return docBuilder;

    try {
     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     docBuilder = factory.newDocumentBuilder();
    }
    catch(ParserConfigurationException pcex) {
      pcex.printStackTrace();
    }

    return docBuilder;
  }

  /**
   * Write XML data to an OutputStream
   *
   * @param element XML Element to write
   * @param stream OutputStream to write to
   */
  public static void writeXML(Element element,OutputStream stream)
  {
    org.apache.axis.utils.XMLUtils.ElementToStream(element,stream);
  }

  /**
   * Write XML data to a Writer
   *
   * @param element XML Element to write
   * @param writer Writer to write to
   */
  public static void writeXML(Element element,Writer writer)
  {
    org.apache.axis.utils.XMLUtils.ElementToWriter(element,writer);
  }
}