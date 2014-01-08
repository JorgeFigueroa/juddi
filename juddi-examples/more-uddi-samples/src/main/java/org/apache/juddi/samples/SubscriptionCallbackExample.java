/*
 * Copyright 2001-2013 The Apache Software Foundation.
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
package org.apache.juddi.samples;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Holder;
import org.apache.juddi.jaxb.PrintUDDI;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.subscription.ISubscriptionCallback;
import org.apache.juddi.v3.client.subscription.SubscriptionCallbackListener;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.sub_v3.DeleteSubscription;
import org.uddi.sub_v3.Subscription;
import org.uddi.sub_v3.SubscriptionFilter;
import org.uddi.sub_v3.SubscriptionResultsList;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDISubscriptionPortType;

/**
 * This class shows you how to use the client side Subscription callback
 * interface which fires up an embedded jetty server hosting a callback web
 * service. It also automates most of the subscription management work.
 *
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 */
public class SubscriptionCallbackExample implements ISubscriptionCallback, Runnable {

        private static UDDISecurityPortType security = null;

        private static UDDIPublicationPortType publish = null;
        private static UDDIInquiryPortType inquiry = null;
        private static UDDIClient c = null;
        private static UDDISubscriptionPortType subscription = null;
        private static UDDIClerk clerk = null;

        public SubscriptionCallbackExample() {
                try {

                        c = new UDDIClient("META-INF/simple-publish-uddi.xml");
                        c.start();
                        clerk = c.getClerk("default");
                        //!IMPORTANT, this is needed so that the Subscription listener endpoint can register itself correctly
                        TModel createKeyGenator = UDDIClerk.createKeyGenator("uddi:org.apache.juddi:test:keygenerator", "Test domain", "en");
                        clerk.register(createKeyGenator);
                        Transport transport = c.getTransport();
                        // Now you create a reference to the UDDI API
                        security = transport.getUDDISecurityService();
                        publish = transport.getUDDIPublishService();
                        inquiry = transport.getUDDIInquiryService();
                        subscription = transport.getUDDISubscriptionService();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void Fire() throws Exception {

                //this will fireup a jetty server and host a UDDI Subscription callback Listener service
                //and register it per the config file
                BindingTemplate start = SubscriptionCallbackListener.start(c, "default");

                //register our code for the callback part
                SubscriptionCallbackListener.registerCallback(this);

                //login
                //credentials come from the config file
                String token = clerk.getAuthToken(clerk.getUDDINode().getSecurityUrl());

                //Set up a subscription using the 'start' BindingTemplate
                Holder<List<Subscription>> subs = new Holder<List<Subscription>>();
                subs.value = new ArrayList<Subscription>();
                Subscription s = new Subscription();
                s.setBindingKey(start.getBindingKey());
                s.setBrief(false);
                s.setSubscriptionFilter(new SubscriptionFilter());
                s.getSubscriptionFilter().setFindBusiness(new FindBusiness());
                s.getSubscriptionFilter().getFindBusiness().getName().add(new Name(UDDIConstants.WILDCARD, null));
                s.getSubscriptionFilter().getFindBusiness().setFindQualifiers(new FindQualifiers());
                s.getSubscriptionFilter().getFindBusiness().getFindQualifiers().getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
                subs.value.add(s);

                //save the subscription
                subscription.saveSubscription(token, subs);

                Runtime.getRuntime().addShutdownHook(new Thread(this));
                //Do some useful activities here, perhap something to trigger the callback
                System.out.println("Running and waiting for changes, press Ctrl-C to break!");
                while (running) {
                        Thread.sleep(1000);
                }
                //stop and unregister the callback when we're done
                SubscriptionCallbackListener.stop(c, "default", start.getBindingKey());

                //clean up the subscription
                DeleteSubscription ds = new DeleteSubscription();
                ds.setAuthInfo(token);
                ds.getSubscriptionKey().add(subs.value.get(0).getSubscriptionKey());
                subscription.deleteSubscription(ds);
        }
        boolean running = true;

        public static void main(String args[]) throws Exception {
                new SubscriptionCallbackExample().Fire();
        }

        @Override
        public void HandleCallback(SubscriptionResultsList body) {
                System.out.println("callback received");
                System.out.println(new PrintUDDI<SubscriptionResultsList>().print(body));
        }

        @Override
        public void NotifyEndpointStopped() {

                System.out.println("stopped");
        }

        @Override
        public void run() {
                System.out.println("Break signal caught!");
                running = false;
        }
}
