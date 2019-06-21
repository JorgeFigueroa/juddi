/*
 * Copyright 2019 The Apache Software Foundation.
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
package org.apache.juddi.security.rbac;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.xml.ws.WebServiceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.api_v3.Action;
import org.apache.juddi.api_v3.GetPermissionsMessageRequest;
import org.apache.juddi.api_v3.GetPermissionsMessageResponse;
import org.apache.juddi.api_v3.Permission;
import org.apache.juddi.api_v3.SetPermissionsMessageRequest;
import org.apache.juddi.api_v3.SetPermissionsMessageResponse;
import org.apache.juddi.config.PersistenceManager;
import org.apache.juddi.config.ResourceConfig;
import org.apache.juddi.model.UddiEntity;
import org.apache.juddi.model.UddiEntityPublisher;
import org.apache.juddi.security.AccessLevel;
import org.apache.juddi.security.IAccessControl;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.OperationalInfo;
import org.uddi.api_v3.RelatedBusinessInfo;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.TModel;
import org.uddi.api_v3.TModelInfo;
import org.uddi.v3_service.DispositionReportFaultMessage;

/**
 * RBAC functionality, to be pulled from J2EE container, such as Tomcat or
 * Jboss, for obtaining user roles.<br><br>
 * Permissions are then calculated based on an inheritance model, similar to
 * most systems. I.e.<br><br>
 * If a permission doesn't exist for a binding, the service's permission set
 * then applies. If the service's permission set is not defined, then the
 * business's permission set applies.
 * <br><br>
 * If the requestor does not have permission for the entity, all content is
 * nulled out and replaced with a 'redacted' string. This will preserve
 * functionality for pagination operations, limits, offsets, etc.
 *
 * @author Alex O'Ree
 * @since 3.4
 */
public class RoleBasedAccessControlImpl implements IAccessControl {

    private static final Log log = LogFactory.getLog(RoleBasedAccessControlImpl.class);
    private static final String REDACTED = ResourceConfig.getGlobalMessage("rbac.redacted");

    private void redact(BusinessService bs) {
        bs.setBusinessKey(REDACTED);
        bs.setServiceKey(REDACTED);
        bs.setBindingTemplates(null);
        bs.setCategoryBag(null);
        bs.getDescription().clear();
        bs.getSignature().clear();
        bs.getName().clear();
        bs.getName().add(new Name(REDACTED, "en"));

    }

    private boolean hasReadAccess(WebServiceContext ctx, List<RbacRulesModel> rules) {
        for (RbacRulesModel r : rules) {
            if (ctx.isUserInRole(r.getContainerRole())) {
                if (r.getAccessLevel() == AccessLevel.NONE) //explicit deny
                {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private List<RbacRulesModel> getPermissionSet(String serviceKey) {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<RbacRulesModel> set = new ArrayList<>();
        try {
            tx.begin();

            Query createQuery = em.createQuery("select c from RbacRulesModel c where c.uddiEntityId=:id");
            createQuery.setParameter("id", serviceKey);
            set = createQuery.getResultList();

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        return set;

    }

    private UddiEntity loadEntity(String serviceKey, Class clazz) {

        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            try {
                return (UddiEntity) em.find(clazz, serviceKey);
            } catch (ClassCastException e) {
            }

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        return null;
    }

    private void redact(BindingTemplate bt) {
        bt.setBindingKey(REDACTED);
        bt.getDescription().clear();
        bt.getSignature().clear();
        bt.setHostingRedirector(null);
        bt.setAccessPoint(null);
        bt.setTModelInstanceDetails(null);
        bt.setAccessPoint(null);
        bt.setCategoryBag(null);
    }

    @Override
    public List<BusinessService> filterServices(WebServiceContext ctx, UddiEntityPublisher username, List<BusinessService> items) {

        //load access rules from database
        for (BusinessService bs : items) {
            //get the permission for this entity.
            UddiEntity ue = loadEntity(bs.getServiceKey(), org.apache.juddi.model.BusinessService.class);
            if (ue == null) {
                redact(bs);
                continue;   //access denied
            }
            if (username.isOwner(ue)) {
                //keep it
                continue;
            }

            List<RbacRulesModel> rules = getPermissionSet(bs.getServiceKey());
            if (rules.isEmpty()) {
                //get the rules for the parent business
                rules = getPermissionSet(bs.getBusinessKey());

            }
            if (rules.isEmpty()) {
                redact(bs);
                continue;   //access denied
            }
            if (!hasReadAccess(ctx, rules)) {
                redact(bs); //also access denied, either no matching role or an explicit deny
                continue;
            }
            filterBindingTemplates(ctx, username, bs.getBindingTemplates().getBindingTemplate());

        }
        return new ArrayList(items);
    }

    @Override
    public List<BusinessEntity> filterBusinesses(WebServiceContext ctx, UddiEntityPublisher username, List<BusinessEntity> items) {

        //load access rules from database
        for (BusinessEntity bs : items) {
            //get the permission for this entity.
            UddiEntity ue = loadEntity(bs.getBusinessKey(), org.apache.juddi.model.BusinessService.class);
            if (ue == null) {
                redact(bs);
                continue;   //access denied
            }
            if (username.isOwner(ue)) {
                //keep it
                continue;
            }

            List<RbacRulesModel> rules = getPermissionSet(bs.getBusinessKey());
            if (rules.isEmpty()) {
                redact(bs);
                continue;   //access denied
            }
            if (!hasReadAccess(ctx, rules)) {
                redact(bs); //also access denied, either no matching role or an explicit deny
                continue;
            }
            filterServices(ctx, username, bs.getBusinessServices().getBusinessService());

        }
        return new ArrayList(items);

    }

    @Override
    public List<BusinessInfo> filterBusinessInfo(WebServiceContext ctx, UddiEntityPublisher username, List<BusinessInfo> items) {
        return new ArrayList(items);
    }

    @Override
    public List<TModel> filterTModels(WebServiceContext ctx, UddiEntityPublisher username, List<TModel> items) {
        return new ArrayList(items);
    }

    @Override
    public List<BindingTemplate> filterBindingTemplates(WebServiceContext ctx, UddiEntityPublisher username, List<BindingTemplate> items) {

        //load access rules from database
        for (BindingTemplate bs : items) {
            //get the permission for this entity.
            UddiEntity ue = loadEntity(bs.getBindingKey(), org.apache.juddi.model.BindingTemplate.class);
            if (ue == null) {
                redact(bs);
                continue;   //access denied
            }
            if (username.isOwner(ue)) {
                //keep it
                continue;
            }

            List<RbacRulesModel> rules = getPermissionSet(bs.getBindingKey());
            if (rules.isEmpty()) {
                rules = getPermissionSet(bs.getServiceKey());
            }

            if (rules.isEmpty()) {
                redact(bs);
                continue;   //access denied
            }
            if (!hasReadAccess(ctx, rules)) {
                redact(bs); //also access denied, either no matching role or an explicit deny
            }

        }
        return new ArrayList(items);

    }

    @Override
    public List<RelatedBusinessInfo> filtedRelatedBusinessInfos(WebServiceContext ctx, UddiEntityPublisher username, List<RelatedBusinessInfo> items) {
        //TODO
        return new ArrayList(items);
    }

    @Override
    public List<ServiceInfo> filterServiceInfo(WebServiceContext ctx, UddiEntityPublisher username, List<ServiceInfo> items) {

        for (ServiceInfo si : items) {
            UddiEntity ue = loadEntity(si.getServiceKey(), org.apache.juddi.model.BusinessService.class);
            if (ue == null) {
                si.setServiceKey(REDACTED);
                continue;   //access denied
            }
            if (username.isOwner(ue)) {
                //keep it
                continue;
            }

            List<RbacRulesModel> rules = getPermissionSet(si.getServiceKey());

            if (!rules.isEmpty() && !hasReadAccess(ctx, rules)) {
                si.setServiceKey(REDACTED);
            }
            if (rules.isEmpty()) {
                rules = getPermissionSet(si.getBusinessKey());
                if (rules.isEmpty()) {
                    si.setBusinessKey(REDACTED);
                } else {
                    if (!hasReadAccess(ctx, rules)) {
                        si.setBusinessKey(REDACTED);
                    }
                }
            }

        }
        return new ArrayList<>(items);
    }

    @Override
    public List<TModelInfo> filterTModelInfo(WebServiceContext ctx, UddiEntityPublisher username, List<TModelInfo> items) {
        //TODO
        return new ArrayList(items);
    }

    @Override
    public List<OperationalInfo> filterOperationalInfo(WebServiceContext ctx, UddiEntityPublisher username, List<OperationalInfo> items) {
        //TODO
        return new ArrayList(items);
    }

    private void redact(BusinessEntity bs) {
        bs.setBusinessKey(REDACTED);
        bs.setBusinessServices(null);
        bs.setCategoryBag(null);
        bs.setContacts(null);
        bs.setDiscoveryURLs(null);
        bs.setIdentifierBag(null);
        bs.getDescription().clear();
        bs.getSignature().clear();
        bs.getName().clear();
        bs.getName().add(new Name(REDACTED, "en"));
    }

    @Override
    public GetPermissionsMessageResponse getPermissions(GetPermissionsMessageRequest arg0) throws DispositionReportFaultMessage, RemoteException {

        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<RbacRulesModel> set = new ArrayList<>();
        try {
            tx.begin();
            Query createQuery = null;
            if (arg0.getEntityId() != null && arg0.getEntityId().length() > 0) {
                createQuery = em.createQuery("select c from RbacRulesModel c where c.uddiEntityId=:id");
                createQuery.setParameter("id", arg0.getEntityId());
            } else {
                createQuery = em.createQuery("select c from RbacRulesModel c");
            }
            
            set = createQuery.getResultList();

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        GetPermissionsMessageResponse response = new GetPermissionsMessageResponse();
        for (RbacRulesModel item : set) {
            Permission permission = new Permission();
            permission.setEntityId(item.getUddiEntityId());
            permission.setLevel(org.apache.juddi.api_v3.AccessLevel.fromValue(item.getAccessLevel().name()));
            permission.setAction(Action.NOOP);
            permission.setTarget(item.getContainerRole());
            //TODO permission.setType(item.);
            response.getLevel().add(permission);
        }

        return response;
    }

    @Override
    public SetPermissionsMessageResponse setPermissions(SetPermissionsMessageRequest arg0) throws DispositionReportFaultMessage, RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}