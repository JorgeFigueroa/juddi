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


package org.uddi.v3_service;

import java.lang.reflect.UndeclaredThrowableException;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.soap.Detail;
import javax.xml.ws.WebFault;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.uddi.api_v3.DispositionReport;
import org.uddi.api_v3.ErrInfo;
import org.uddi.api_v3.Result;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.5-b03-
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "dispositionReport", targetNamespace = "urn:uddi-org:api_v3")
public class DispositionReportFaultMessage
    extends RemoteException
{
	private static final long serialVersionUID = -3901821587689888649L;
	private static transient Log log = LogFactory.getLog(DispositionReportFaultMessage.class);
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private DispositionReport faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public DispositionReportFaultMessage(String message, DispositionReport faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public DispositionReportFaultMessage(String message, DispositionReport faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.uddi.api_v3.DispositionReport
     */
    public DispositionReport getFaultInfo() {
        return faultInfo;
    }
    
    /** 
     * Convenience method to figure out if the Exception at hand contains a
     * DispositionReport. Disposition report will be null if none can be found.
     * 
     * @param e the Exception at hang
     * @return DispositionReport if one can be found, or null if it is not.
     */
    public static DispositionReport getDispositionReport(Exception e) {
    	DispositionReport report = null;
    	if (e instanceof DispositionReportFaultMessage) {
    		DispositionReportFaultMessage faultMsg = (DispositionReportFaultMessage) e;
    		report = faultMsg.getFaultInfo();
    	} else if (e instanceof SOAPFaultException) {
    		SOAPFaultException soapFault = (SOAPFaultException) e;
    		Detail detail = soapFault.getFault().getDetail();
    		if (detail != null && detail.getFirstChild()!=null) {
    			try {
    				report =  new DispositionReport(detail.getFirstChild());
    			} catch (JAXBException je) {
    				log.error("Could not unmarshall detail to a DispositionReport");
    			}
    		}
    	} else if (e instanceof UndeclaredThrowableException) {
    		UndeclaredThrowableException ute =(UndeclaredThrowableException) e;
    		if (ute.getUndeclaredThrowable()!=null && ute.getUndeclaredThrowable().getCause()!=null
    		    && ute.getUndeclaredThrowable().getCause().getCause() instanceof DispositionReportFaultMessage) {
    			DispositionReportFaultMessage faultMsg = (DispositionReportFaultMessage) ute.getUndeclaredThrowable().getCause().getCause();
	    		report = faultMsg.getFaultInfo();
    		}
    	} else {
    		log.error("Unsupported Exception: It's not a known instance of DispositionReport. ",e);
          
    	}
    	return report;
    }
}
