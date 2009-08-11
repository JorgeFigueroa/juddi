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
 *
 */
package org.apache.juddi.portlets.client;

import org.apache.juddi.portlets.client.model.Publisher;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 *  @author <a href="mailto:kstam@apache.org">Kurt T Stam</a>
 */
public class JUDDIPublisher implements EntryPoint, Login {

	private static JUDDIPublisher singleton;
	
	private MenuBarPanel menuBar = new MenuBarPanel();
	private DockPanel dockPanel = new DockPanel();
	private LoginPanel loginPanel = new LoginPanel(this);
	private PublisherListPanel publisherListPanel = new PublisherListPanel();
	private PublisherPanel publisherPanel = null;

	public static JUDDIPublisher getInstance() {
		return singleton;
	}
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() { 
		singleton = this;
		
		dockPanel.setSpacing(8);
		menuBar.setVisible(false);
		menuBar.setHeight("20px");
		dockPanel.add(menuBar,DockPanel.NORTH);
		
		loginPanel.setVisible(false);
		dockPanel.add(loginPanel,DockPanel.WEST);
		
		publisherListPanel.setVisible(false);
		dockPanel.add(publisherListPanel,DockPanel.CENTER);
		
		RootPanel.get("publisher").add(dockPanel);
	}
	
	public void login() {
		String token = loginPanel.getToken();
		if (token == null ) {
			loginPanel.setVisible(true);
			publisherListPanel.setVisible(false);
			menuBar.setVisible(false);
		} else {
			loginPanel.setVisible(false);
			menuBar.setVisible(true);
			publisherListPanel.setVisible(true);
			String publisherId = loginPanel.getPublisherId();
			publisherListPanel.listPublishers(token, publisherId);
		}
	}
	
	public void displayPublisher(Publisher publisher) {
		if (publisherPanel!=null ) dockPanel.remove(publisherPanel);
		publisherPanel = new PublisherPanel(publisher);
		dockPanel.add(publisherPanel,DockPanel.EAST);
		String token = loginPanel.getToken();
		String publisherId = loginPanel.getPublisherId();
		publisherListPanel.listPublishers(token, publisherId);
	}
	
	public void setSelectedPublisher(String selectedPublisherId) {
		publisherListPanel.setSelectedPublisher(selectedPublisherId);
	}
	
	public void hidePublisher() {
		publisherPanel.setVisible(false);
		String token = loginPanel.getToken();
		String publisherId = loginPanel.getPublisherId();
		publisherListPanel.selectRow(0);
		if (publisherPanel!=null ) dockPanel.remove(publisherPanel);
		publisherPanel=null;
		publisherListPanel.listPublishers(token, publisherId);
	}

	public String getToken() {
		return loginPanel.getToken();
	}
	
	public void savePublisher() {
		if (publisherPanel!=null) {
			publisherPanel.savePublisher(getToken());
		}
	}
	
	public void newPublisher() {
		if (publisherPanel!=null ) dockPanel.remove(publisherPanel);
		publisherPanel = new PublisherPanel(null);
		dockPanel.add(publisherPanel,DockPanel.EAST);
		publisherListPanel.selectRow(0);
	}
	
	public void deletePublisher() {
		if (publisherPanel!=null) {
			publisherPanel.deletePublisher(getToken());
		}
	}


	
	
	
}