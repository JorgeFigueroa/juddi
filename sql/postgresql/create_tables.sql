CREATE TABLE BUSINESS_ENTITY
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	AUTHORIZED_NAME VARCHAR(255) NOT NULL,
	PUBLISHER_ID VARCHAR(20) NULL,
	OPERATOR VARCHAR(255) NOT NULL,
	LAST_UPDATE TIMESTAMP NOT NULL DEFAULT now(),
	CONSTRAINT PK_BUSINESS_ENTITY PRIMARY KEY (BUSINESS_KEY)
);

CREATE TABLE BUSINESS_DESCR
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	BUSINESS_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_BUS_DESCR_01 FOREIGN KEY (BUSINESS_KEY)
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY) 
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BUSINESS_DESCR PRIMARY KEY (BUSINESS_KEY,BUSINESS_DESCR_ID)
);

CREATE TABLE BUSINESS_CATEGORY
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CATEGORY_ID INT NOT NULL,
	TMODEL_KEY_REF VARCHAR(41) NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_BUS_CAT_01 FOREIGN KEY (BUSINESS_KEY)
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BUSINESS_CATEGORY PRIMARY KEY (BUSINESS_KEY,CATEGORY_ID)
);

CREATE TABLE BUSINESS_IDENTIFIER
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	IDENTIFIER_ID INT NOT NULL,
	TMODEL_KEY_REF VARCHAR(41) NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_BUS_ID_01 FOREIGN KEY (BUSINESS_KEY)
	REFERENCES BUSINESS_ENTITY (BUSINESS_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BUSINESS_IDENTIFIER PRIMARY KEY (BUSINESS_KEY,IDENTIFIER_ID)
);

CREATE TABLE BUSINESS_NAME
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	BUSINESS_NAME_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	NAME VARCHAR(255) NOT NULL,
	CONSTRAINT FK_BUS_NAME_01 FOREIGN KEY (BUSINESS_KEY)
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BUSINESS_NAME PRIMARY KEY (BUSINESS_KEY,BUSINESS_NAME_ID)
);

CREATE TABLE CONTACT
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CONTACT_ID INT NOT NULL,
	USE_TYPE VARCHAR(255) NULL,
	PERSON_NAME VARCHAR(255) NOT NULL,
	CONSTRAINT FK_CONTACT_01 FOREIGN KEY (BUSINESS_KEY)
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_CONTACT PRIMARY KEY (BUSINESS_KEY,CONTACT_ID)
);

CREATE TABLE CONTACT_DESCR
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CONTACT_ID INT NOT NULL,
	CONTACT_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_CONTACT_DESCR_01 FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
		REFERENCES CONTACT (BUSINESS_KEY,CONTACT_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_CONTACT_DESCR PRIMARY KEY (BUSINESS_KEY,CONTACT_ID,CONTACT_DESCR_ID)
);

CREATE TABLE ADDRESS
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CONTACT_ID INT NOT NULL,
	ADDRESS_ID INT NOT NULL,
	USE_TYPE VARCHAR(255) NULL,
	SORT_CODE VARCHAR(10) NULL,
	TMODEL_KEY VARCHAR(41) NULL,
	CONSTRAINT FK_ADDRESS_01 FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
		REFERENCES CONTACT (BUSINESS_KEY,CONTACT_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_ADDRESS PRIMARY KEY (BUSINESS_KEY,CONTACT_ID,ADDRESS_ID)
);

CREATE TABLE ADDRESS_LINE
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CONTACT_ID INT NOT NULL,
	ADDRESS_ID INT NOT NULL,
	ADDRESS_LINE_ID INT NOT NULL,
	LINE VARCHAR(80) NOT NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NULL,
	CONSTRAINT FK_ADDR_LINE_01 FOREIGN KEY (BUSINESS_KEY,CONTACT_ID,ADDRESS_ID)
		REFERENCES ADDRESS (BUSINESS_KEY,CONTACT_ID,ADDRESS_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_ADDRESS_LINE PRIMARY KEY (BUSINESS_KEY,CONTACT_ID,ADDRESS_ID,ADDRESS_LINE_ID)
);

CREATE TABLE EMAIL
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CONTACT_ID INT NOT NULL,
	EMAIL_ID INT NOT NULL,
	USE_TYPE VARCHAR(255) NULL,
	EMAIL_ADDRESS VARCHAR(255) NOT NULL,
	CONSTRAINT FK_EMAIL_01 FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
		REFERENCES CONTACT (BUSINESS_KEY,CONTACT_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_EMAIL PRIMARY KEY (BUSINESS_KEY,CONTACT_ID,EMAIL_ID)
);

CREATE TABLE PHONE
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	CONTACT_ID INT NOT NULL,
	PHONE_ID INT NOT NULL,
	USE_TYPE VARCHAR(255) NULL,
	PHONE_NUMBER VARCHAR(50) NOT NULL,
	CONSTRAINT FK_PHONE_01 FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
		REFERENCES CONTACT (BUSINESS_KEY,CONTACT_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_PHONE PRIMARY KEY (BUSINESS_KEY,CONTACT_ID,PHONE_ID)
);

CREATE TABLE DISCOVERY_URL
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	DISCOVERY_URL_ID INT NOT NULL,
	USE_TYPE VARCHAR(255) NOT NULL,
	URL VARCHAR(255) NOT NULL,
	CONSTRAINT FK_DISCOVERY_URL_01 FOREIGN KEY (BUSINESS_KEY)
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_DISCOVERY_URL PRIMARY KEY (BUSINESS_KEY,DISCOVERY_URL_ID)
);

CREATE TABLE BUSINESS_SERVICE
(
	BUSINESS_KEY VARCHAR(41) NOT NULL,
	SERVICE_KEY VARCHAR(41) NOT NULL,
	LAST_UPDATE TIMESTAMP NOT NULL DEFAULT now(),
	CONSTRAINT FK_BUS_SERVICE_01 FOREIGN KEY (BUSINESS_KEY)
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BUSINESS_SERVICE PRIMARY KEY (SERVICE_KEY)
);

CREATE TABLE SERVICE_DESCR
(
	SERVICE_KEY VARCHAR(41) NOT NULL,
	SERVICE_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_SERVICE_DESCR_01 FOREIGN KEY (SERVICE_KEY)
		REFERENCES BUSINESS_SERVICE (SERVICE_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_SERVICE_DESCR PRIMARY KEY (SERVICE_KEY,SERVICE_DESCR_ID)
);

CREATE TABLE SERVICE_CATEGORY
(
	SERVICE_KEY VARCHAR(41) NOT NULL,
	CATEGORY_ID INT NOT NULL,
	TMODEL_KEY_REF VARCHAR(41) NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_SERVICE_CATEGORY_01 FOREIGN KEY (SERVICE_KEY)
		REFERENCES BUSINESS_SERVICE (SERVICE_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_SERVICE_CATEGORY PRIMARY KEY (SERVICE_KEY,CATEGORY_ID)
);

CREATE TABLE SERVICE_NAME
(
	SERVICE_KEY VARCHAR(41) NOT NULL,
	SERVICE_NAME_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	NAME VARCHAR(255) NOT NULL,
	CONSTRAINT FK_SERVICE_NAME_01 FOREIGN KEY (SERVICE_KEY)
		REFERENCES BUSINESS_SERVICE (SERVICE_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_SERVICE_NAME PRIMARY KEY (SERVICE_KEY,SERVICE_NAME_ID)
);

CREATE TABLE BINDING_TEMPLATE
(
	SERVICE_KEY VARCHAR(41) NOT NULL,
	BINDING_KEY VARCHAR(41) NOT NULL,
	ACCESS_POINT_TYPE VARCHAR(20) NULL,
	ACCESS_POINT_URL VARCHAR(255) NULL,
	HOSTING_REDIRECTOR VARCHAR(255) NULL,
	LAST_UPDATE TIMESTAMP NOT NULL DEFAULT now(),
	CONSTRAINT FK_BINDING_TEMPLATE_01 FOREIGN KEY (SERVICE_KEY)
		REFERENCES BUSINESS_SERVICE (SERVICE_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BINDING_TEMPLATE PRIMARY KEY (BINDING_KEY)
);

CREATE TABLE BINDING_CATEGORY
(
	BINDING_KEY VARCHAR(41) NOT NULL,
	CATEGORY_ID INT NOT NULL,
	TMODEL_KEY_REF VARCHAR(41) NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_BINDING_CATEGORY_01 FOREIGN KEY (BINDING_KEY)
		REFERENCES BINDING_TEMPLATE (BINDING_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BINDING_CATEGORY PRIMARY KEY (BINDING_KEY,CATEGORY_ID)
);


CREATE TABLE BINDING_DESCR
(
	BINDING_KEY VARCHAR(41) NOT NULL,
	BINDING_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_BINDING_DESCR_01 FOREIGN KEY (BINDING_KEY)
		REFERENCES BINDING_TEMPLATE (BINDING_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_BINDING_DESCR PRIMARY KEY (BINDING_KEY,BINDING_DESCR_ID)
);

CREATE TABLE TMODEL_INSTANCE_INFO
(
	BINDING_KEY VARCHAR(41) NOT NULL,
	TMODEL_INSTANCE_INFO_ID INT NOT NULL,
	TMODEL_KEY VARCHAR(41) NOT NULL,
	OVERVIEW_URL VARCHAR(255) NULL,
	INSTANCE_PARMS VARCHAR(255) NULL,
	CONSTRAINT FK_TMODEL_INSTANCE_INFO_01 FOREIGN KEY (BINDING_KEY)
		REFERENCES BINDING_TEMPLATE (BINDING_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_TMODEL_INSTANCE_INFO PRIMARY KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
);

CREATE TABLE TMODEL_INSTANCE_INFO_DESCR
(
	BINDING_KEY VARCHAR(41) NOT NULL,
	TMODEL_INSTANCE_INFO_ID INT NOT NULL,
	TMODEL_INSTANCE_INFO_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_TMODEL_INSTANCE_INFO_DESCR_01 FOREIGN KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
		REFERENCES TMODEL_INSTANCE_INFO (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_TMODEL_INSTANCE_INFO_DESCR PRIMARY KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID,TMODEL_INSTANCE_INFO_DESCR_ID)
);

CREATE TABLE INSTANCE_DETAILS_DESCR
(
	BINDING_KEY VARCHAR(41) NOT NULL,
	TMODEL_INSTANCE_INFO_ID INT NOT NULL,
	INSTANCE_DETAILS_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_INSTANCE_DETAILS_DESCR_01 FOREIGN KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
		REFERENCES TMODEL_INSTANCE_INFO (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_INSTANCE_DETAILS_DESCR PRIMARY KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID,INSTANCE_DETAILS_DESCR_ID)
);

CREATE TABLE INSTANCE_DETAILS_DOC_DESCR
(
	BINDING_KEY VARCHAR(41) NOT NULL,
	TMODEL_INSTANCE_INFO_ID INT NOT NULL,
	INSTANCE_DETAILS_DOC_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_INSTANCE_DETAILS_DOC_DESCR_01 FOREIGN KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
		REFERENCES TMODEL_INSTANCE_INFO (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_INSTANCE_DETAILS_DOC_DESCR PRIMARY KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID,INSTANCE_DETAILS_DOC_DESCR_ID)
);

CREATE TABLE TMODEL
(
	TMODEL_KEY VARCHAR(41) NOT NULL,
	AUTHORIZED_NAME VARCHAR(255) NOT NULL,
	PUBLISHER_ID VARCHAR(20) NULL,
	OPERATOR VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	OVERVIEW_URL VARCHAR(255) NULL,
	LAST_UPDATE TIMESTAMP NOT NULL DEFAULT NOW(),
	CONSTRAINT PK_TMODEL PRIMARY KEY (TMODEL_KEY)
);

CREATE TABLE TMODEL_DESCR
(
	TMODEL_KEY VARCHAR(41) NOT NULL,
	TMODEL_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_TMODEL_DESCR_01 FOREIGN KEY (TMODEL_KEY)
		REFERENCES TMODEL (TMODEL_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_TMODEL_DESCR PRIMARY KEY (TMODEL_KEY,TMODEL_DESCR_ID)
);

CREATE TABLE TMODEL_CATEGORY
(
	TMODEL_KEY VARCHAR(41) NOT NULL,
	CATEGORY_ID INT NOT NULL,
	TMODEL_KEY_REF VARCHAR(255) NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_TMODEL_CATEGORY_01 FOREIGN KEY (TMODEL_KEY)
		REFERENCES TMODEL (TMODEL_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_TMODEL_CATEGORY PRIMARY KEY (TMODEL_KEY,CATEGORY_ID)
);

CREATE TABLE TMODEL_IDENTIFIER
(
	TMODEL_KEY VARCHAR(41) NOT NULL,
	IDENTIFIER_ID INT NOT NULL,
	TMODEL_KEY_REF VARCHAR(255) NULL,
	KEY_NAME VARCHAR(255) NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	CONSTRAINT FK_TMODEL_IDENTIFIER_01 FOREIGN KEY (TMODEL_KEY)
		REFERENCES TMODEL (TMODEL_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_TMODEL_IDENTIFIER PRIMARY KEY (TMODEL_KEY,IDENTIFIER_ID)
);

CREATE TABLE TMODEL_DOC_DESCR
(
	TMODEL_KEY VARCHAR(41) NOT NULL,
	TMODEL_DOC_DESCR_ID INT NOT NULL,
	LANG_CODE VARCHAR(2) NULL,
	DESCR VARCHAR(255) NOT NULL,
	CONSTRAINT FK_TMODEL_DOC_DESCR_01 FOREIGN KEY (TMODEL_KEY)
		REFERENCES TMODEL (TMODEL_KEY)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT PK_TMODEL_DOC_DESCR PRIMARY KEY (TMODEL_KEY,TMODEL_DOC_DESCR_ID)
);

CREATE TABLE PUBLISHER_ASSERTION
(
	FROM_KEY VARCHAR(41) NOT NULL,
	TO_KEY VARCHAR(41) NOT NULL,
	TMODEL_KEY VARCHAR(41) NOT NULL,
	KEY_NAME VARCHAR(255) NOT NULL,
	KEY_VALUE VARCHAR(255) NOT NULL,
	FROM_CHECK VARCHAR(5) NOT NULL,
	TO_CHECK VARCHAR(5) NOT NULL,
	CONSTRAINT FK_PUBLISHER_ASSERTION_01 FOREIGN KEY (FROM_KEY) 
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY),
	CONSTRAINT FK_PUBLISHER_ASSERTION_02 FOREIGN KEY (TO_KEY) 
		REFERENCES BUSINESS_ENTITY (BUSINESS_KEY),
	CONSTRAINT PK_PUBLISHER_ASSERTION PRIMARY KEY (FROM_KEY,TO_KEY)
);

CREATE TABLE PUBLISHER
(
	PUBLISHER_ID VARCHAR(20) NOT NULL,
	PUBLISHER_NAME VARCHAR(255) NOT NULL,
	LAST_NAME VARCHAR(150) NULL,
	FIRST_NAME VARCHAR(100) NULL,
	MIDDLE_INIT VARCHAR(5) NULL,
	WORK_PHONE VARCHAR(50) NULL,
	MOBILE_PHONE VARCHAR(50) NULL,
	PAGER VARCHAR(50) NULL,
	EMAIL_ADDRESS VARCHAR(255) NULL,
	ADMIN VARCHAR(5) NULL,
	ENABLED VARCHAR(5) NULL,
	CONSTRAINT PK_PUBLISHER PRIMARY KEY (PUBLISHER_ID)
);

CREATE TABLE AUTH_TOKEN
(
	AUTH_TOKEN VARCHAR(51) NOT NULL,
	PUBLISHER_ID VARCHAR(20) NOT NULL,
	PUBLISHER_NAME VARCHAR(255) NOT NULL,
	CREATED TIMESTAMP NOT NULL,
	LAST_USED TIMESTAMP NOT NULL,
	NUMBER_OF_USES INT NOT NULL,
	TOKEN_STATE INT NOT NULL,
	CONSTRAINT PK_AUTH_TOKEN PRIMARY KEY (AUTH_TOKEN)
);

CREATE TABLE MONITOR
(
	REMOTE_HOST VARCHAR(51) NOT NULL,
	REQUEST_URI VARCHAR(255) NOT NULL,
	CALLED_FUNCTION VARCHAR(51) NOT NULL,
	UDDI_VERSION VARCHAR(51) NOT NULL,
	LOG_TIME DATE NOT NULL,
	AUTH_TOKEN VARCHAR(51) NULL, 
	FAULT VARCHAR(255) NULL
);
