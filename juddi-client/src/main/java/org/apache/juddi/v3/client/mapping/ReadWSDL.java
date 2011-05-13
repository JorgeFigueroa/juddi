/*
 * Copyright 2001-2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.juddi.v3.client.mapping;

import java.net.URL;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import org.apache.juddi.v3.client.ClassUtil;

import com.ibm.wsdl.factory.WSDLFactoryImpl;

/**
 * @author <a href="mailto:kstam@apache.org">Kurt T Stam</a>
 */
public class ReadWSDL {
	
	public Definition readWSDL(String fileName) throws WSDLException {
		WSDLFactory factory = WSDLFactoryImpl.newInstance();
		WSDLReader reader = factory.newWSDLReader();
		URL wsdlURL = ClassUtil.getResource(fileName, this.getClass());
		Definition wsdlDefinition = reader.readWSDL(wsdlURL.toExternalForm());
		return wsdlDefinition;
	}
	
	
}
