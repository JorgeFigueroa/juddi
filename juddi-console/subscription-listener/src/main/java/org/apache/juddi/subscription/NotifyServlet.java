/*
 * Copyright 2001-2009 The Apache Software Foundation.
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
package org.apache.juddi.subscription;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juddi.util.NotificationList;


/**
 * This servlet is used to initialize the jUDDI webapp on
 * startup and cleanup the jUDDI webapp on shutdown.
 * 
 * @author <a href="mailto:tcunning@apache.org">Tom Cunningham</a>
 */
public class NotifyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws
		ServletException, IOException {
		StringBuffer sb = new StringBuffer();

		Vector nl = NotificationList.getInstance().getNotifications();
		if (nl.size() != 0) {
			for (int i = 0; i<nl.size(); i++) {
				sb.append(nl.get(i));
			}	
		}
		nl.clear();
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
	}
}