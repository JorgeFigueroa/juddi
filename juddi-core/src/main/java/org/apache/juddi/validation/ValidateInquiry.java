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

import java.util.HashSet;
import java.util.List;
import java.util.Hashtable;

import javax.xml.bind.JAXBElement;

import org.apache.juddi.api.datatype.GetPublisherDetail;
import org.uddi.api_v3.GetBusinessDetail;
import org.uddi.api_v3.GetOperationalInfo;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.GetTModelDetail;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindTModel;
import org.uddi.api_v3.FindRelatedBusinesses;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.ObjectFactory;
import org.uddi.api_v3.TModelBag;

import org.uddi.v3_service.DispositionReportFaultMessage;

import org.apache.juddi.error.ErrorMessage;
import org.apache.juddi.error.FatalErrorException;
import org.apache.juddi.error.InvalidKeyPassedException;
import org.apache.juddi.error.ValueNotAllowedException;
import org.apache.juddi.error.InvalidCombinationException;
import org.apache.juddi.model.UddiEntityPublisher;
import org.apache.juddi.query.util.FindQualifiers;

/**
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 */
public class ValidateInquiry extends ValidateUDDIApi {

	public ValidateInquiry(UddiEntityPublisher publisher) {
		super(publisher);
	}

	public void validateGetPublisherDetail(GetPublisherDetail body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> publisherIdList = body.getPublisherId();
		if (publisherIdList == null || publisherIdList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String publisherId : publisherIdList) {
			boolean inserted = dupCheck.add(publisherId);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", publisherId));
		}
	}
	
	public void validateGetBusinessDetail(GetBusinessDetail body) throws DispositionReportFaultMessage {

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
		}
	}
	
	public void validateGetServiceDetail(GetServiceDetail body) throws DispositionReportFaultMessage {

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
		}
	}
	
	public void validateGetBindingDetail(GetBindingDetail body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getBindingKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
		}
	}
	
	public void validateGetTModelDetail(GetTModelDetail body) throws DispositionReportFaultMessage {

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
		}
	}

	public void validateGetOperationalInfo(GetOperationalInfo body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getEntityKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
		}
	}
	
	
	public void validateFindBusiness(FindBusiness body) throws DispositionReportFaultMessage  {
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));

		if (body.getCategoryBag() == null && body.getFindTModel() == null && body.getTModelBag() == null && body.getName().size() == 0 &&
			body.getIdentifierBag() == null && body.getDiscoveryURLs() == null && body.getFindRelatedBusinesses() == null)
			throw new FatalErrorException(new ErrorMessage("errors.findbusiness.NoInput"));

		validateFindQualifiers(body.getFindQualifiers());
		validateTModelBag(body.getTModelBag());
		validateFindTModel(body.getFindTModel(), true);
		validateFindRelatedBusinesses(body.getFindRelatedBusinesses(), true);
		validateDiscoveryUrls(body.getDiscoveryURLs());
		validateIdentifierBag(body.getIdentifierBag());
		validateCategoryBag(body.getCategoryBag());
		
	}
	
	public void validateFindService(FindService body) throws DispositionReportFaultMessage  {
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));

		if (body.getCategoryBag() == null && body.getFindTModel() == null && body.getTModelBag() == null && body.getName().size() == 0)
			throw new FatalErrorException(new ErrorMessage("errors.findservice.NoInput"));

		validateFindQualifiers(body.getFindQualifiers());
		validateTModelBag(body.getTModelBag());
		validateFindTModel(body.getFindTModel(), true);
		validateCategoryBag(body.getCategoryBag());
		
	}
	
	public void validateFindBinding(FindBinding body) throws DispositionReportFaultMessage  {
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));

		if (body.getCategoryBag() == null && body.getFindTModel() == null && body.getTModelBag() == null)
			throw new FatalErrorException(new ErrorMessage("errors.findbinding.NoInput"));

		validateFindQualifiers(body.getFindQualifiers());
		validateTModelBag(body.getTModelBag());
		validateFindTModel(body.getFindTModel(), true);
		validateCategoryBag(body.getCategoryBag());
		
		
	}
	
	public void validateFindTModel(FindTModel body, boolean nullAllowed) throws DispositionReportFaultMessage  {
		if (body == null) {
			// When FindTModel objects are embedded in other find calls, null is allowed.
			if (nullAllowed)
				return;
			else
				throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		}

		if (body.getCategoryBag() == null && body.getIdentifierBag() == null && body.getName() == null)
			throw new FatalErrorException(new ErrorMessage("errors.findtmodel.NoInput"));

		validateFindQualifiers(body.getFindQualifiers());
		validateIdentifierBag(body.getIdentifierBag());
		validateCategoryBag(body.getCategoryBag());
	}
	
	public void validateFindRelatedBusinesses(FindRelatedBusinesses body, boolean nullAllowed) throws DispositionReportFaultMessage  {
		if (body == null) {
			// When FindRelatedBusinesses objects are embedded in other find calls, null is allowed.
			if (nullAllowed)
				return;
			else
				throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		}

		if ((body.getBusinessKey() == null  || body.getBusinessKey().length() == 0 ) && 
			(body.getFromKey() == null || body.getFromKey().length() == 0) && 
			(body.getToKey() == null || body.getToKey().length() == 0))
			throw new FatalErrorException(new ErrorMessage("errors.findrelatedbusiness.NoInput"));
		
		boolean businessKeyExists = false;
		boolean fromKeyExists = false;
		if (body.getBusinessKey() != null && body.getBusinessKey().length() > 0) {
			businessKeyExists = true;

		}
		if (body.getFromKey() != null && body.getFromKey().length() > 0) {
			fromKeyExists = true;
			if (businessKeyExists)
				throw new FatalErrorException(new ErrorMessage("errors.findrelatedbusiness.MultipleInput"));
			
		}
		if (body.getToKey() != null && body.getToKey().length() > 0) {
			if (businessKeyExists || fromKeyExists)
				throw new FatalErrorException(new ErrorMessage("errors.findrelatedbusiness.MultipleInput"));
		}
		
		KeyedReference keyedRef = body.getKeyedReference();
		if (keyedRef != null) {
			if (keyedRef.getTModelKey() == null || keyedRef.getTModelKey().length() == 0 ||
				keyedRef.getKeyName() == null || keyedRef.getKeyName().length() == 0 ||
				keyedRef.getKeyValue() == null || keyedRef.getKeyValue().length() == 0)
				throw new ValueNotAllowedException(new ErrorMessage("errors.findrelatedbusiness.BlankKeyedRef"));
		}
			
	}
	
	public void validateTModelBag(TModelBag tmodelBag) throws DispositionReportFaultMessage {
		// tmodelBag is optional
		if (tmodelBag == null)
			return;
		
		if (tmodelBag.getTModelKey() == null || tmodelBag.getTModelKey().size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelbag.NoInput"));
		
	}
	
	public void validateDiscoveryUrls(org.uddi.api_v3.DiscoveryURLs discUrls) throws DispositionReportFaultMessage {
		// Discovery Urls is optional
		if (discUrls == null)
			return;

		// If discUrls does exist, it must have at least one element
		List<org.uddi.api_v3.DiscoveryURL> discUrlList = discUrls.getDiscoveryURL();
		if (discUrlList == null || discUrlList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.discurls.NoInput"));
	}	
	
	public void validateCategoryBag(org.uddi.api_v3.CategoryBag categories) throws DispositionReportFaultMessage {
		
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

	public void validateIdentifierBag(org.uddi.api_v3.IdentifierBag identifiers) throws DispositionReportFaultMessage {
		
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
	
	public void validateKeyedReferenceTypes(JAXBElement<?> elem) throws DispositionReportFaultMessage {
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
	
	
	private void validateFindQualifiers(org.uddi.api_v3.FindQualifiers findQualifiers) throws DispositionReportFaultMessage {
		if (findQualifiers == null)
			return;
		
		List<String> fqList = findQualifiers.getFindQualifier();
		if (fqList == null || fqList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.findqualifiers.NoInput"));
		
		
		Hashtable<String, String> fqTable = new Hashtable<String, String>();
		for (String fq : fqList) {
			String result = fqTable.put(fq.toUpperCase(), fq.toUpperCase());
			if (result != null)
				throw new ValueNotAllowedException(new ErrorMessage("errors.findqualifiers.DuplicateValue", result));
			
			// Invalid combo: andAllKeys, orAllKeys, and orLikeKeys
			if (fq.equalsIgnoreCase(FindQualifiers.AND_ALL_KEYS) || fq.equalsIgnoreCase(FindQualifiers.AND_ALL_KEYS_TMODEL)) {
				if (fqTable.get(FindQualifiers.OR_ALL_KEYS.toUpperCase()) != null || fqTable.get(FindQualifiers.OR_ALL_KEYS_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.OR_ALL_KEYS));
					
				if (fqTable.get(FindQualifiers.OR_LIKE_KEYS.toUpperCase()) != null || fqTable.get(FindQualifiers.OR_LIKE_KEYS_TMODEL.toUpperCase()) != null)
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.OR_LIKE_KEYS));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.OR_ALL_KEYS) || fq.equalsIgnoreCase(FindQualifiers.OR_ALL_KEYS_TMODEL)) {
				if (fqTable.get(FindQualifiers.AND_ALL_KEYS.toUpperCase()) != null || fqTable.get(FindQualifiers.AND_ALL_KEYS_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.AND_ALL_KEYS));
					
				if (fqTable.get(FindQualifiers.OR_LIKE_KEYS.toUpperCase()) != null || fqTable.get(FindQualifiers.OR_LIKE_KEYS_TMODEL.toUpperCase()) != null)
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.OR_LIKE_KEYS));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.OR_LIKE_KEYS) || fq.equalsIgnoreCase(FindQualifiers.OR_LIKE_KEYS_TMODEL)) {
				if (fqTable.get(FindQualifiers.AND_ALL_KEYS.toUpperCase()) != null || fqTable.get(FindQualifiers.AND_ALL_KEYS_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.AND_ALL_KEYS));
					
				if (fqTable.get(FindQualifiers.OR_ALL_KEYS.toUpperCase()) != null || fqTable.get(FindQualifiers.OR_ALL_KEYS_TMODEL.toUpperCase()) != null)
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.OR_ALL_KEYS));
			}

			// Invalid combo: sortByNameAsc and sortByNameDesc
			if (fq.equalsIgnoreCase(FindQualifiers.SORT_BY_NAME_ASC) || fq.equalsIgnoreCase(FindQualifiers.SORT_BY_NAME_ASC_TMODEL)) {
				if (fqTable.get(FindQualifiers.SORT_BY_NAME_DESC.toUpperCase()) != null || fqTable.get(FindQualifiers.SORT_BY_NAME_DESC_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.SORT_BY_NAME_DESC));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.SORT_BY_NAME_DESC) || fq.equalsIgnoreCase(FindQualifiers.SORT_BY_NAME_DESC_TMODEL)) {
				if (fqTable.get(FindQualifiers.SORT_BY_NAME_ASC.toUpperCase()) != null || fqTable.get(FindQualifiers.SORT_BY_NAME_ASC_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.SORT_BY_NAME_ASC));
			}
			
			// Invalid combo: sortByDateAsc and sortByDateDesc
			if (fq.equalsIgnoreCase(FindQualifiers.SORT_BY_DATE_ASC) || fq.equalsIgnoreCase(FindQualifiers.SORT_BY_DATE_ASC_TMODEL)) {
				if (fqTable.get(FindQualifiers.SORT_BY_DATE_DESC.toUpperCase()) != null || fqTable.get(FindQualifiers.SORT_BY_DATE_DESC_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.SORT_BY_DATE_DESC));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.SORT_BY_DATE_DESC) || fq.equalsIgnoreCase(FindQualifiers.SORT_BY_DATE_DESC_TMODEL)) {
				if (fqTable.get(FindQualifiers.SORT_BY_DATE_ASC.toUpperCase()) != null || fqTable.get(FindQualifiers.SORT_BY_DATE_ASC_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.SORT_BY_DATE_ASC));
			}
			
			// Invalid combo: combineCategoryBags, serviceSubset and bindingSubset 
			if (fq.equalsIgnoreCase(FindQualifiers.COMBINE_CATEGORY_BAGS) || fq.equalsIgnoreCase(FindQualifiers.COMBINE_CATEGORY_BAGS_TMODEL)) {
				if (fqTable.get(FindQualifiers.SERVICE_SUBSET.toUpperCase()) != null || fqTable.get(FindQualifiers.SERVICE_SUBSET_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.SERVICE_SUBSET));
					
				if (fqTable.get(FindQualifiers.BINDING_SUBSET.toUpperCase()) != null || fqTable.get(FindQualifiers.BINDING_SUBSET_TMODEL.toUpperCase()) != null)
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.BINDING_SUBSET));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.SERVICE_SUBSET) || fq.equalsIgnoreCase(FindQualifiers.SERVICE_SUBSET_TMODEL)) {
				if (fqTable.get(FindQualifiers.COMBINE_CATEGORY_BAGS.toUpperCase()) != null || fqTable.get(FindQualifiers.COMBINE_CATEGORY_BAGS_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.COMBINE_CATEGORY_BAGS));
					
				if (fqTable.get(FindQualifiers.BINDING_SUBSET.toUpperCase()) != null || fqTable.get(FindQualifiers.BINDING_SUBSET_TMODEL.toUpperCase()) != null)
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.BINDING_SUBSET));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.BINDING_SUBSET) || fq.equalsIgnoreCase(FindQualifiers.BINDING_SUBSET_TMODEL)) {
				if (fqTable.get(FindQualifiers.SERVICE_SUBSET.toUpperCase()) != null || fqTable.get(FindQualifiers.SERVICE_SUBSET_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.SERVICE_SUBSET));
					
				if (fqTable.get(FindQualifiers.COMBINE_CATEGORY_BAGS.toUpperCase()) != null || fqTable.get(FindQualifiers.COMBINE_CATEGORY_BAGS_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.COMBINE_CATEGORY_BAGS));
			}
			
			// Invalid combo: exactMatch and approximateMatch
			if (fq.equalsIgnoreCase(FindQualifiers.EXACT_MATCH) || fq.equalsIgnoreCase(FindQualifiers.EXACT_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.APPROXIMATE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.APPROXIMATE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.APPROXIMATE_MATCH));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.APPROXIMATE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.APPROXIMATE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.EXACT_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.EXACT_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.EXACT_MATCH));
			}
			
			// Invalid combo: exactMatch and caseInsensitiveMatch
			if (fq.equalsIgnoreCase(FindQualifiers.EXACT_MATCH) || fq.equalsIgnoreCase(FindQualifiers.EXACT_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.CASE_INSENSITIVE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.CASE_INSENSITIVE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.CASE_INSENSITIVE_MATCH));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.CASE_INSENSITIVE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.CASE_INSENSITIVE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.EXACT_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.EXACT_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.EXACT_MATCH));
			}

			// Invalid combo: binarySort and UTS-10 
			if (fq.equalsIgnoreCase(FindQualifiers.BINARY_SORT) || fq.equalsIgnoreCase(FindQualifiers.BINARY_SORT_TMODEL)) {
				if (fqTable.get(FindQualifiers.UTS_10.toUpperCase()) != null || fqTable.get(FindQualifiers.UTS_10_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.UTS_10));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.UTS_10) || fq.equalsIgnoreCase(FindQualifiers.UTS_10_TMODEL)) {
				if (fqTable.get(FindQualifiers.BINARY_SORT.toUpperCase()) != null || fqTable.get(FindQualifiers.BINARY_SORT_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.BINARY_SORT));
			}

			// Invalid combo: diacriticSensitiveMatch and diacriticInsensitiveMatch
			if (fq.equalsIgnoreCase(FindQualifiers.DIACRITIC_SENSITIVE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.DIACRITIC_SENSITIVE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.DIACRITIC_INSENSITIVE_MATCH));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.DIACRITIC_SENSITIVE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.DIACRITIC_SENSITIVE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.DIACRITIC_SENSITIVE_MATCH));
			}

			// Invalid combo: exactMatch and diacriticInsensitiveMatch
			if (fq.equalsIgnoreCase(FindQualifiers.EXACT_MATCH) || fq.equalsIgnoreCase(FindQualifiers.EXACT_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.DIACRITIC_INSENSITIVE_MATCH));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.DIACRITIC_INSENSITIVE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.EXACT_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.EXACT_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.EXACT_MATCH));
			}
			
			// Invalid combo: caseSensitiveSort and caseInsensitiveSort
			if (fq.equalsIgnoreCase(FindQualifiers.CASE_SENSITIVE_SORT) || fq.equalsIgnoreCase(FindQualifiers.CASE_SENSITIVE_SORT_TMODEL)) {
				if (fqTable.get(FindQualifiers.CASE_INSENSITIVE_SORT.toUpperCase()) != null || fqTable.get(FindQualifiers.CASE_INSENSITIVE_SORT_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.CASE_INSENSITIVE_SORT));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.CASE_INSENSITIVE_SORT) || fq.equalsIgnoreCase(FindQualifiers.CASE_INSENSITIVE_SORT_TMODEL)) {
				if (fqTable.get(FindQualifiers.CASE_SENSITIVE_SORT.toUpperCase()) != null || fqTable.get(FindQualifiers.CASE_SENSITIVE_SORT_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.CASE_SENSITIVE_SORT));
			}
			
			// Invalid combo: caseSensitiveMatch and caseInsensitiveMatch
			if (fq.equalsIgnoreCase(FindQualifiers.CASE_SENSITIVE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.CASE_SENSITIVE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.CASE_INSENSITIVE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.CASE_INSENSITIVE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.CASE_INSENSITIVE_MATCH));
			}
			else if (fq.equalsIgnoreCase(FindQualifiers.CASE_INSENSITIVE_MATCH) || fq.equalsIgnoreCase(FindQualifiers.CASE_INSENSITIVE_MATCH_TMODEL)) {
				if (fqTable.get(FindQualifiers.CASE_SENSITIVE_MATCH.toUpperCase()) != null || fqTable.get(FindQualifiers.CASE_SENSITIVE_MATCH_TMODEL.toUpperCase()) != null) 
					throw new InvalidCombinationException(new ErrorMessage("errors.findqualifiers.InvalidCombo", fq + " & " + FindQualifiers.CASE_SENSITIVE_MATCH));
			}
			
		}
	}
}