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

package org.apache.juddi.query;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.juddi.query.util.DynamicQuery;
import org.apache.juddi.query.util.FindQualifiers;
import org.uddi.api_v3.IdentifierBag;

/**
 * Returns the list of tModel keys possessing the keyedReferences in the passed identifier bag.
 * Output is restricted by list of tModel keys passed in.  If null, all tModels are searched.
 * Output is produced by building the appropriate JPA query based on input and find qualifiers.
 * 
 * From specification:
 * "This is a list of identifier references in the form of keyedReference elements. The returned 
 * tModelList contains tModelInfo elements whose associated tModels match any of the identifiers passed (logical OR by default). 
 * Specifying the appropriate findQualifiers can override this behavior."
 *  
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 */
public class FindTModelByIdentifierQuery {
	
	private static final String ENTITY_NAME_CHILD = "TmodelIdentifier";
	
	private static FindEntityByIdentifierQuery findQuery;
	
	static {
		findQuery = new FindEntityByIdentifierQuery(TModelQuery.ENTITY_NAME, 
													TModelQuery.ENTITY_ALIAS, 
													TModelQuery.KEY_NAME, 
													TModelQuery.ENTITY_FIELD, 
													ENTITY_NAME_CHILD);
	}
	
	public static List<?> select(EntityManager em, FindQualifiers fq, IdentifierBag identifiers, List<?> keysIn) {
		return findQuery.select(em, fq, identifiers, keysIn, new DynamicQuery.Parameter(TModelQuery.ENTITY_ALIAS + ".deleted", new Boolean(false), DynamicQuery.PREDICATE_EQUALS));
	}
	
}