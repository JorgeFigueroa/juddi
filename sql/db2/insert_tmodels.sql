INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','Administrator','jUDDI.org','uddi-org:types','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#UDDItypes',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',0,'en','UDDI Type Taxonomy');
 
INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',0,'en','Taxonomy used to categorize Service Descriptions.');
 
INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');
 
INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384','Administrator','jUDDI.org','unspsc-org:unspsc:3-1','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#UNSPSC31',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384',0,'en','Product Taxonomy: UNSPSC (Version 3.1)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384',0,'en','This tModel defines the UNSPSC product taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634','Administrator','jUDDI.org','unspsc-org:unspsc','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#UNSPSC',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',0,'en','Product Taxonomy: UNSPSC (Version 7.3)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',0,'en','This tModel defines Version 7.3 of the UNSPSC product taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','Checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2','Administrator','jUDDI.org','ntis-gov:naics:1997','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#NAICS',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',0,'en','Business Taxonomy: NAICS(1997 Release)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',0,'en','This tModel defines the NAICS industry taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88','Administrator','jUDDI.org','uddi-org:iso-ch:3166-1999','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#ISO3166',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',0,'en','ISO 3166-1:1997 and 3166-2:1998. Codes for names of countries and their subdivisions. Part 1: Country codes. Part 2:Country subdivision codes. Update newsletters include ISO 3166-1 V-1 (1998-02-05), V-2 (1999-10-01), ISO 3166-2 I-1 (1998)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',0,'en','Taxonomy used to categorize entries by geographic location.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4','Administrator','jUDDI.org','uddi-org:general_keywords','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#GenKW',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4',0,'en','Special taxonomy consisting of namespace identifiers and the keywords associated with the namespaces');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4',0,'en','This tModel defines an unidentified taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9','Administrator','jUDDI.org','uddi-org:owningBusiness','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#owningBusiness',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',0,'en','A pointer to a businessEntity that owns the tagged data.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',0,'en','This tModel indicates the businessEntity that published or owns the tagged tModel. Used with tModels to establish an "owned" relationship with a registered businessEntity.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03','Administrator','jUDDI.org','uddi-org:relationships','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#Relationships',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03',0,'en','Starter set classifications of businessEntity relationships');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03',0,'en','This tModel is used to describe business relationships. Used in the publisher assertion messages.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','relationship');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55','Administrator','jUDDI.org','uddi-org:operators','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#Operators',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',0,'en','Taxonomy for categorizing the businessEntity of an operator of a registry.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',0,'en','This checked value set is used to identify UDDI operators.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E','Administrator','jUDDI.org','uddi-org:isReplacedBy','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#IsReplacedBy',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',0,'en','An identifier system used to point (using UDDI keys) to the tModel (or businessEntity) that is the logical replacement for the one in which isReplacedBy is used');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',0,'en','This is a checked value set.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','identifier');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823','Administrator','jUDDI.org','dnb-com:D-U-N-S','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#D-U-N-S',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823',0,'en','Dun&Bradstreet D-U-N-S� Number');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823',0,'en','This tModel is used for the Dun&Bradstreet D-U-N-S� Number identifier.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','identifier');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039','Administrator','jUDDI.org','thomasregister-com:supplierID','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#Thomas',CURRENT TIMESTAMP);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039',0,'en','Thomas Registry Suppliers');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039',0,'en','This tModel is used for the Thomas Register supplier identifier codes.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','identifier');
