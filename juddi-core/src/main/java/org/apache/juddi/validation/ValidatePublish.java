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

package org.apache.juddi.validation;

import java.util.List;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBElement;

import org.uddi.api_v3.ObjectFactory;
import org.uddi.api_v3.DeleteBusiness;
import org.uddi.api_v3.DeleteBinding;
import org.uddi.api_v3.DeletePublisherAssertions;
import org.uddi.api_v3.DeleteService;
import org.uddi.api_v3.DeleteTModel;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.api_v3.SaveBinding;
import org.uddi.api_v3.SaveTModel;
import org.uddi.v3_service.DispositionReportFaultMessage;

import org.apache.juddi.error.ErrorMessage;
import org.apache.juddi.error.FatalErrorException;
import org.apache.juddi.error.InvalidKeyPassedException;
import org.apache.juddi.error.AssertionNotFoundException;
import org.apache.juddi.error.KeyUnavailableException;
import org.apache.juddi.error.ValueNotAllowedException;
import org.apache.juddi.error.InvalidProjectionException;


/**
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 */
public class ValidatePublish {

	public static void validateDeleteBusiness(EntityManager em, DeleteBusiness body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getBusinessKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BusinessEntity.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.BusinessNotFound", entityKey));
			
		}
	}

	public static void validateDeleteService(EntityManager em, DeleteService body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getServiceKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BusinessService.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ServiceNotFound", entityKey));
			
		}
	}

	public static void validateDeleteBinding(EntityManager em, DeleteBinding body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getBindingKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		// Checking for duplicates and existence
		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BindingTemplate.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.BindingNotFound", entityKey));
			
		}
	}
	
	public static void validateDeleteTModel(EntityManager em, DeleteTModel body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getTModelKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.Tmodel.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.TModelNotFound", entityKey));
			
		}
	}

	public static void validateDeletePublisherAssertions(EntityManager em, DeletePublisherAssertions body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.PublisherAssertion> entityList = body.getPublisherAssertion();
		if (entityList == null || entityList.size() == 0)
			throw new AssertionNotFoundException(new ErrorMessage("errors.assertion.NoPubAssertions"));

		for (org.uddi.api_v3.PublisherAssertion entity : entityList) {
			// TODO: duplicate check?
			
			org.apache.juddi.model.PublisherAssertionId pubAssertionId = new org.apache.juddi.model.PublisherAssertionId(entity.getFromKey(), entity.getToKey());
			Object obj = em.find(org.apache.juddi.model.BusinessEntity.class, pubAssertionId);
			if (obj == null)
				throw new AssertionNotFoundException(new ErrorMessage("errors.assertion.AssertionNotFound", entity.getFromKey() + ", " + entity.getToKey()));
			
		}
	}

	public static void validateSaveBusiness(EntityManager em, SaveBusiness body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.BusinessEntity> entityList = body.getBusinessEntity();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savebusiness.NoInput"));
		
		for (org.uddi.api_v3.BusinessEntity entity : entityList) {
			validateBusinessEntity(em, entity);
		}
	}
	
	public static void validateSaveService(EntityManager em, SaveService body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.BusinessService> entityList = body.getBusinessService();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.saveservice.NoInput"));
		
		for (org.uddi.api_v3.BusinessService entity : entityList) {
			// Entity specific data validation
			validateBusinessService(em, entity, null);
		}
	}
	
	public static void validateSaveBinding(EntityManager em, SaveBinding body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.BindingTemplate> entityList = body.getBindingTemplate();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savebinding.NoInput"));
		
		for (org.uddi.api_v3.BindingTemplate entity : entityList) {
			validateBindingTemplate(em, entity, null);
		}
	}

	public static void validateSaveTModel(EntityManager em, SaveTModel body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.TModel> entityList = body.getTModel();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savetmodel.NoInput"));
		
		for (org.uddi.api_v3.TModel entity : entityList) {
			validateTModel(em, entity);
		}
	}
	
	public static void validateBusinessEntity(EntityManager em, org.uddi.api_v3.BusinessEntity businessEntity) throws DispositionReportFaultMessage {
		
		// A supplied businessService can't be null
		if (businessEntity == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.businessentity.NullInput"));
		
		boolean entityExists = false;
		String entityKey = businessEntity.getBusinessKey();
		if (entityKey == null || entityKey.length() == 0) {
			// TODO: apply key generation strategy
			entityKey = "";
			businessEntity.setBusinessKey(entityKey);
		}
		else {
			// TODO: Test that key value is valid for publisher
			//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));

			Object obj = em.find(org.apache.juddi.model.BusinessEntity.class, entityKey);
			if (obj != null)
				entityExists = true;
		}

		if (!entityExists) {
			// TODO: Check to make sure key isn't used by another entity.  If exists in operation info then it is.
			//if (em.find(OperationalInfo.class, entityKey) != null)
			//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
		}
		
		// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
		
		validateNames(businessEntity.getName());
		validateDiscoveryUrls(businessEntity.getDiscoveryURLs());
		validateContacts(businessEntity.getContacts());
		validateCategoryBag(businessEntity.getCategoryBag());
		validateIdentifierBag(businessEntity.getIdentifierBag());

		validateBusinessServices(em, businessEntity.getBusinessServices(), businessEntity);
		
	}

	public static void validateBusinessServices(EntityManager em, org.uddi.api_v3.BusinessServices businessServices, org.uddi.api_v3.BusinessEntity parent) 
					throws DispositionReportFaultMessage {
		// Business services is optional
		if (businessServices == null)
			return;
		
		List<org.uddi.api_v3.BusinessService> businessServiceList = businessServices.getBusinessService();
		if (businessServiceList == null || businessServiceList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.businessservices.NoInput"));
		
		for (org.uddi.api_v3.BusinessService businessService : businessServiceList) {
			validateBusinessService(em, businessService, parent);
		}
			
	}

	public static void validateBusinessService(EntityManager em, org.uddi.api_v3.BusinessService businessService, org.uddi.api_v3.BusinessEntity parent) 
					throws DispositionReportFaultMessage {

		// A supplied businessService can't be null
		if (businessService == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.businessservice.NullInput"));
		
		// Retrieve the service's passed key
		String entityKey = businessService.getServiceKey();
		
		// The parent key is either supplied or provided by the higher call to the parent entity save.  If the passed-in parent's business key differs from 
		// the (non-null) business key retrieved from the service, then we have a possible service projection.
		String parentKey = businessService.getBusinessKey();
		boolean isProjection = false;
		if (parent != null) {
			if (parentKey != null && parentKey.length() > 0) {
				if (!parentKey.equals(parent.getBusinessKey())) {
					// Possible projected service - if we have differing parent businesses but a service key was not provided, this is an error as it is not possible 
					// for the business that doesn't "own" the service to generate the key for it.
					if (entityKey == null || entityKey.length() == 0)
						throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ServiceKeyNotProvidedWithProjection", parentKey + ", " + parent.getBusinessKey()));

					isProjection = true;
				}
			}
			else
				parentKey = parent.getBusinessKey();
		}
		
		// Projections don't require as rigorous testing as only the projected service's business key and service key are examined for validity.
		if (isProjection) {
			Object obj = em.find(org.apache.juddi.model.BusinessService.class, entityKey);
			// Can't project a service that doesn't exist!
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ProjectedServiceNotFound", parentKey + ", " + entityKey));
			else {
				// If the supplied business key doesn't match the existing service's business key, the projection is invalid.
				org.apache.juddi.model.BusinessService bs = (org.apache.juddi.model.BusinessService)obj;
				if (!businessService.getBusinessKey().equals(bs.getBusinessEntity().getBusinessKey()))
					throw new InvalidProjectionException(new ErrorMessage("errors.invalidprojection.ParentMismatch", businessService.getBusinessKey() + ", " + bs.getBusinessEntity().getBusinessKey()));
			}
		}
		else {

			boolean entityExists = false;
			if (entityKey == null || entityKey.length() == 0) {
				// TODO: apply key generation strategy
				entityKey = "";
				businessService.setServiceKey(entityKey);
			}
			else {
				// TODO: Test that key value is valid for publisher
				//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));
				
				Object obj = em.find(org.apache.juddi.model.BusinessService.class, entityKey);
				if (obj != null) {
					entityExists = true;
					// If existing service trying to be saved has a different parent key, then we have a problem
					// TODO: moving services is allowed according to spec?
					org.apache.juddi.model.BusinessService bs = (org.apache.juddi.model.BusinessService)obj;
					if (!parentKey.equals(bs.getBusinessEntity().getBusinessKey()))
						throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.businessservice.ParentMismatch", parentKey + ", " + bs.getBusinessEntity().getBusinessKey()));
				}
				
			}
			
			// Parent key must be passed if this is a new entity
			if (!entityExists) {
				if (parentKey == null || parentKey.length() == 0)
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentBusinessNotFound", parentKey));
			}

			// If parent key IS passed, whether new entity or not, it must be valid.  Additionally, the current publisher must be the owner of the parent.  Note that
			// if a parent ENTITY was passed in, then we don't need to check for any of this since this is part of a higher call.
			if (parentKey != null) {
				if (parent == null) {
					Object parentTemp = em.find(org.apache.juddi.model.BusinessEntity.class, parentKey);
					if (parentTemp == null)
						throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentBusinessNotFound", parentKey));

					// TODO: Test that publisher is owner of parent: UserMismatchException 

				}
			}

			if (!entityExists) {
				// TODO: Check to make sure key isn't used by another entity.  If exists in operation info then it is.
				//if (em.find(OperationalInfo.class, entityKey) != null)
				//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
			}
			
			// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
			
			validateNames(businessService.getName());
			validateCategoryBag(businessService.getCategoryBag());
			
			validateBindingTemplates(em, businessService.getBindingTemplates(), businessService);
		}
		
	}

	public static void validateBindingTemplates(EntityManager em, org.uddi.api_v3.BindingTemplates bindingTemplates, org.uddi.api_v3.BusinessService parent) 
					throws DispositionReportFaultMessage {
		// Binding templates is optional
		if (bindingTemplates == null)
			return;
	
		List<org.uddi.api_v3.BindingTemplate> bindingTemplateList = bindingTemplates.getBindingTemplate();
		if (bindingTemplateList == null || bindingTemplateList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.bindingtemplates.NoInput"));
	
		for (org.uddi.api_v3.BindingTemplate bindingTemplate : bindingTemplateList) {
			validateBindingTemplate(em, bindingTemplate, parent);
		}
	
	}
	
	public static void validateBindingTemplate(EntityManager em, org.uddi.api_v3.BindingTemplate bindingTemplate, org.uddi.api_v3.BusinessService parent) 
					throws DispositionReportFaultMessage {

		// A supplied bindingTemplate can't be null
		if (bindingTemplate == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.bindingtemplate.NullInput"));

		// Retrieve the binding's passed key
		String entityKey = bindingTemplate.getBindingKey();

		// The parent key is either supplied or provided by the higher call to the parent entity save.  If it is provided in both instances, if they differ, an 
		// error occurs.
		String parentKey = bindingTemplate.getServiceKey();
		if (parent != null) {
			if (parentKey != null && parentKey.length() > 0) {
				if (!parentKey.equals(parent.getBusinessKey()))
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.bindingtemplate.ParentMismatch", parentKey + ", " + parent.getBusinessKey()));
			}
			else
				parentKey = parent.getBusinessKey();
		}
		
		boolean entityExists = false;
		if (entityKey == null || entityKey.length() == 0) {
			// TODO: apply key generation strategy
			entityKey = "";
		}
		else {
			// TODO: Test that key value is valid for publisher
			//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BindingTemplate.class, entityKey);
			if (obj != null) {
				entityExists = true;
				// If existing binding trying to be saved has a different parent key, then we have a problem
				// TODO: moving bindings is allowed according to spec?
				org.apache.juddi.model.BindingTemplate bt = (org.apache.juddi.model.BindingTemplate)obj;
				if (!parentKey.equals(bt.getBusinessService().getServiceKey()))
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.bindingtemplate.ParentMismatch", parentKey + ", " + bt.getBusinessService().getServiceKey()));
			}
			
		}
		
		// Parent key must be passed if this is a new entity
		if (!entityExists) {
			if (parentKey == null || parentKey.length() == 0)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentServiceNotFound", parentKey));
		}

		// If parent key IS passed, whether new entity or not, it must be valid.  Additionally, the current publisher must be the owner of the parent.  Note that
		// if a parent ENTITY was passed in, then we don't need to check for any of this since this is part of a higher call.
		if (parentKey != null) {
			if (parent == null) {
				Object parentTemp = em.find(org.apache.juddi.model.BusinessService.class, parentKey);
				if (parentTemp == null)
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentBusinessNotFound", parentKey));

				// TODO: Test that publisher is owner of parent: UserMismatchException 

			}
		}

		if (!entityExists) {
			// TODO: Check to make sure key isn't used by another entity.  If exists in operation info then it is.
			//if (em.find(OperationalInfo.class, entityKey) != null)
			//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
		}
		
		// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
		
		
		if (bindingTemplate.getAccessPoint() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.bindingtemplate.NoAccessPoint"));
		
		validateCategoryBag(bindingTemplate.getCategoryBag());
		validateTModelInstanceDetails(bindingTemplate.getTModelInstanceDetails());
		
		
	}

	public static void validateTModel(EntityManager em, org.uddi.api_v3.TModel tModel) throws DispositionReportFaultMessage {
		// A supplied tModel can't be null
		if (tModel == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodel.NullInput"));
		
		boolean entityExists = false;
		String entityKey = tModel.getTModelKey();
		if (entityKey == null || entityKey.length() == 0) {
			// TODO: apply key generation strategy
			entityKey = "";
			tModel.setTModelKey(entityKey);
		}
		else {
			// TODO: Test that key value is valid for publisher
			//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));

			Object obj = em.find(org.apache.juddi.model.BusinessEntity.class, entityKey);
			if (obj != null)
				entityExists = true;
		}

		if (!entityExists) {
			// TODO: Check to make sure key isn't used by another entity.  If exists in operation info then it is.
			//if (em.find(OperationalInfo.class, entityKey) != null)
			//throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
		}
		
		// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
		
		if (tModel.getName() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodel.NoName"));
		
		validateCategoryBag(tModel.getCategoryBag());
		validateIdentifierBag(tModel.getIdentifierBag());

		List<org.uddi.api_v3.OverviewDoc> overviewDocList = tModel.getOverviewDoc();
		if (overviewDocList != null) {
			for (org.uddi.api_v3.OverviewDoc overviewDoc : overviewDocList)
				validateOverviewDoc(overviewDoc);
		}

	}
	
	public static void validateNames(List<org.uddi.api_v3.Name> names) throws DispositionReportFaultMessage {
		// At least one name is required
		if (names == null || names.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.names.NoInput"));
		
	}
	
	public static void validateContacts(org.uddi.api_v3.Contacts contacts) throws DispositionReportFaultMessage {
		// Contacts is optional
		if (contacts == null)
			return;
		
		// If contacts do exist, at least one contact is required
		List<org.uddi.api_v3.Contact> contactList = contacts.getContact();
		if (contactList == null || contactList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.contacts.NoInput"));
		
		for (org.uddi.api_v3.Contact contact : contactList) {
			validateContact(contact);
		}
		
	}

	public static void validateContact(org.uddi.api_v3.Contact contact) throws DispositionReportFaultMessage {
		// A supplied contact can't be null
		if (contact == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.contact.NullInput"));
		
		// At least one personName is required
		List<org.uddi.api_v3.PersonName> pnameList = contact.getPersonName();
		if (pnameList == null || pnameList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.contact.NoPersonName"));
		
		List<org.uddi.api_v3.Address> addressList = contact.getAddress();
		if (addressList != null) {
			for (org.uddi.api_v3.Address address : addressList) {
				if (address != null) {
					if (address.getAddressLine() == null || address.getAddressLine().size() == 0)
						throw new ValueNotAllowedException(new ErrorMessage("errors.contact.NoAddressLine"));
				}
			}
		}
	}
	
	public static void validateDiscoveryUrls(org.uddi.api_v3.DiscoveryURLs discUrls) throws DispositionReportFaultMessage {
		// Discovery Urls is optional
		if (discUrls == null)
			return;

		// If discUrls does exist, it must have at least one element
		List<org.uddi.api_v3.DiscoveryURL> discUrlList = discUrls.getDiscoveryURL();
		if (discUrlList == null || discUrlList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.discurls.NoInput"));
	}	
	
	public static void validateCategoryBag(org.uddi.api_v3.CategoryBag categories) throws DispositionReportFaultMessage {
		
		// Category bag is optional
		if (categories == null)
			return;
		
		// If category bag does exist, it must have at least one element
		List<JAXBElement<?>> elems = categories.getContent();
		if (elems == null || elems.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.categorybag.NoInput"));
		
		for (JAXBElement<?> elem : elems) {
			validateKeyedReferenceTypes(elem);
		}
	}

	public static void validateIdentifierBag(org.uddi.api_v3.IdentifierBag identifiers) throws DispositionReportFaultMessage {
		
		// Identifier bag is optional
		if (identifiers == null)
			return;
		
		// If category bag does exist, it must have at least one element
		List<org.uddi.api_v3.KeyedReference> keyedRefList = identifiers.getKeyedReference();
		if (keyedRefList == null || keyedRefList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.identifierbag.NoInput"));
		
		for (org.uddi.api_v3.KeyedReference keyedRef : keyedRefList) {
			validateKeyedReferenceTypes(new ObjectFactory().createKeyedReference(keyedRef));
		}
	}
	
	
	public static void validateKeyedReferenceTypes(JAXBElement<?> elem) throws DispositionReportFaultMessage {
		if (elem == null || elem.getValue() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NullInput"));
		
		// Keyed reference groups must contain a tModelKey
		if (elem.getValue() instanceof org.uddi.api_v3.KeyedReferenceGroup) {
			org.uddi.api_v3.KeyedReferenceGroup krg = (org.uddi.api_v3.KeyedReferenceGroup)elem.getValue();
			if (krg.getTModelKey() == null || krg.getTModelKey().length() == 0)
				throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NoTModelKey"));
		}
		// Keyed references must contain a tModelKey and keyValue
		else if (elem.getValue() instanceof org.uddi.api_v3.KeyedReference) {
			org.uddi.api_v3.KeyedReference kr = (org.uddi.api_v3.KeyedReference)elem.getValue();
			if (kr.getTModelKey() == null || kr.getTModelKey().length() == 0)
				throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NoTModelKey"));
			
			if (kr.getKeyValue() == null || kr.getKeyValue().length() == 0)
				throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NoKeyValue"));
		}
	}

	public static void validateTModelInstanceDetails(org.uddi.api_v3.TModelInstanceDetails tmodelInstDetails) throws DispositionReportFaultMessage {

		// tModel Instance Details is optional
		if (tmodelInstDetails == null)
			return;
		
		// If tmodelInstDetails does exist, it must have at least one element
		List<org.uddi.api_v3.TModelInstanceInfo> tmodelInstInfoList = tmodelInstDetails.getTModelInstanceInfo();
		if (tmodelInstInfoList == null || tmodelInstInfoList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelinstdetails.NoInput"));
		
		for (org.uddi.api_v3.TModelInstanceInfo tmodelInstInfo : tmodelInstInfoList) {
			validateTModelInstanceInfo(tmodelInstInfo);
		}
	}

	public static void validateTModelInstanceInfo(org.uddi.api_v3.TModelInstanceInfo tmodelInstInfo) throws DispositionReportFaultMessage {
		// tModel Instance Info can't be null
		if (tmodelInstInfo == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelinstinfo.NullInput"));
		
		// TModel key is required
		if (tmodelInstInfo.getTModelKey() == null || tmodelInstInfo.getTModelKey().length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelinstinfo.NoTModelKey"));
		
		validateInstanceDetails(tmodelInstInfo.getInstanceDetails());
		
	}

	public static void validateInstanceDetails(org.uddi.api_v3.InstanceDetails instDetails) throws DispositionReportFaultMessage {
		// Instance Details is optional
		if (instDetails == null)
			return;
		
		// At least one OverviewDoc or instanceParms must be supplied
		List<JAXBElement<?>> elems = instDetails.getContent();
		if (elems == null || elems.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.instdetails.NoOverviewOrParms"));
		
		// At least one OverviewDoc or instanceParms must be supplied
		boolean overviewOrParmsFound = false;
		for (JAXBElement<?> elem : elems) {
			if (elem.getValue() instanceof org.uddi.api_v3.OverviewDoc) {
				overviewOrParmsFound = true;
				org.uddi.api_v3.OverviewDoc od = (org.uddi.api_v3.OverviewDoc)elem.getValue();
				validateOverviewDoc(od);
			}
			else if (elem.getValue() instanceof String) {
				overviewOrParmsFound = true;
			}
		}

		if (!overviewOrParmsFound)
			throw new ValueNotAllowedException(new ErrorMessage("errors.instdetails.NoOverviewOrParms"));

	}
	
	public static void validateOverviewDoc(org.uddi.api_v3.OverviewDoc overviewDoc) throws DispositionReportFaultMessage {
		// OverviewDoc can't be null
		if (overviewDoc == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.overviewdoc.NullInput"));
		
		// At least one description or overview URL must be supplied
		List<JAXBElement<?>> elems = overviewDoc.getContent();
		if (elems == null || elems.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.overviewdoc.NoDescOrUrl"));
	}

}