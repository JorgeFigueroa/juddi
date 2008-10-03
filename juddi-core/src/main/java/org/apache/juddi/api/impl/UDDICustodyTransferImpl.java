/*
 * Copyright 2001-2008 The Apache Software Foundation.
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
 *
 */

package org.apache.juddi.api.impl;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import org.uddi.custody_v3.DiscardTransferToken;
import org.uddi.custody_v3.KeyBag;
import org.uddi.custody_v3.TransferEntities;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDICustodyTransferPortType;

@WebService(serviceName="UDDICustodyTransferService", 
			endpointInterface="org.uddi.v3_service.UDDICustodyTransferPortType")
public class UDDICustodyTransferImpl implements UDDICustodyTransferPortType {

	public void discardTransferToken(DiscardTransferToken body)
			throws DispositionReportFaultMessage {
		// TODO Auto-generated method stub

	}

	public void getTransferToken(String authInfo, KeyBag keyBag,
			Holder<String> nodeID, Holder<XMLGregorianCalendar> expirationTime,
			Holder<byte[]> opaqueToken) throws DispositionReportFaultMessage {
		// TODO Auto-generated method stub

	}

	public void transferEntities(TransferEntities body)
			throws DispositionReportFaultMessage {
		// TODO Auto-generated method stub

	}

}