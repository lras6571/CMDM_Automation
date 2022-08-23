package com.sysco.qe.common;

import com.sysco.qeutils.common.SUTAPProperties;
import com.sysco.qeutils.common.TestEnvironments;

public class APIConstants {

    /**
     * QCenter
     */
    public static final String TEST_ENV = System.getProperty("test.env", TestEnvironments.EXE_ENVIRONMENT.getEnvironment());
    public static final String TEST_RELEASE = System.getProperty("test.release", "CLOUD_PCI_REG_WEEKLY_BUILD");
    public static final String TEST_PROJECT = System.getProperty("test.project", "PRCP-CLOUD PCI");

    /**
     * QCenter Modules and Features
     */
    public static final String FEATURE_QCENTER = "feature";
    public static final String MODULE_API_CLOUD_PCI_PVT = "CLOUD_PCI_PVT_API";
    public static final String MODULE_API_CLOUD_PCI_GRABBER = "CLOUD_PCI_GRABBER_API";
    public static final String MODULE_API_CLOUD_PCI_TH = "CLOUD_PCI_TH_API";
    public static final String FEATURE_API_PRODUCT_PRICES = "PCI_PVT_API_PRICES_VERIFICATION";
    public static final String FEATURE_API_FILE_UPLOAD = "PCI_GRABBER_API_FILE_UPLOAD";
    public static final String FEATURE_API_FILE_DOWNLOAD = "PCI_GRABBER_API_FILE_DOWNLOAD";
    public static final String FEATURE_API_JOBS_DELETE = "PCI_GRABBER_API_JOBS_DELETE";
    public static final String FEATURE_API_JOBS_SEARCH = "PCI_GRABBER_API_JOBS_SEARCH";
    public static final String FEATURE_API_JOBS_HEALTH_CHECK = "PCI_GRABBER_API_JOBS_HEALTH_CHECK";
    public static final String FEATURE_API_TH = "PCI_TH_API_SEARCH";

    /**
     * API
     */
    public static final String BASE_HTTP_PROTOCOL = System.getProperty("api.protocol", "http://");
    public static final String BASE_HTTPS_PROTOCOL = System.getProperty("api.protocol", "https://");
    public static final String BASE_DOMAIN_STIBO = System.getProperty("stibo.api.host", "syscononprod-sandbox.scloud.stibo.com");
    public static final String BASE_PATH_STIBO = System.getProperty("stibo.base.path", "/restapiv2");

    /**
     * Paths to input and output data files
     */
    public static final String PATH_TO_RESOURCE = System.getProperty("user.dir") + "/src/main/resources/";
    public static final String TEST_DATA_DIRECTORY = "testData/";
    public static final String REQUEST_DATA_DIRECTORY = TEST_DATA_DIRECTORY + "requests/";
    public static final String CSV_DATA_DIRECTORY = TEST_DATA_DIRECTORY + "csv/";
    public static final String INPUT_DATA_JSON_FILE = PATH_TO_RESOURCE + REQUEST_DATA_DIRECTORY + "EntitySearch.json";
    public static final String INPUT_DATA_JSON_FILE2 = PATH_TO_RESOURCE + REQUEST_DATA_DIRECTORY + "EntityFieldUpdate.json";
    public static final String INPUT_DATA_JSON_FILE3 = PATH_TO_RESOURCE + REQUEST_DATA_DIRECTORY + "ApproveObject.json";
    public static final String INPUT_BILL_TO_DATA_CSV_FILE = PATH_TO_RESOURCE + CSV_DATA_DIRECTORY + "FF_BILL_TO_clean_293_temp.csv";
    public static final String INPUT_BILL_TO_DATA_CSV_FILE2 = PATH_TO_RESOURCE + CSV_DATA_DIRECTORY + "FF_BILL_TO_clean_293_temp2.csv";
    public static final String INPUT_SHIP_TO_DATA_CSV_FILE3 = PATH_TO_RESOURCE + CSV_DATA_DIRECTORY + "FF_SHIP_TO_clean_293_temp2.csv";
    public static final int STIBO_IMPORT_WAIT = 120000;


    /**
     * Data Model Configurations
     */
    public static final String SCDLAB_CUST_BILLTO = "scdlab_cust_billto";
    public static final String COMPLETED = "Completed";
    public static final String NOT_COMPLETED = "Not-Completed";
    public static final String CUSTOMER_TYPE = "#Customer_type";
    public static final String BILL_TO_NUMBER = "bill_to_number";
    public static final String BILL_TO_NAME = "bill_to_name";
    public static final String STATUS = "Status";
    public static final String BILL_TO_ADDRESS_1 = "bill_to_Address_1";
    public static final String BILL_TO_ADDRESS_2 = "bill_to_Address_2";
    public static final String BILL_TO_CITY = "bill_to_city";
    public static final String BILL_TO_STATE = "bill_to_state";
    public static final String PHONE = "phone";
    public static final String BILL_TO_POSTALCODE = "bill_to_postalcode";
    public static final String BILL_TO_COUNTRY = "bill_to_country";
    public static final String VENDOR_NUMBER = "vendor_number";
    public static final String LEGAL_COMP_NAME = "legal_comp_name";
    public static final String OPCO_NUMBER = "opco_number";
    public static final String SOURCE_SYSTEM = "source_system";
    public static final String BILL_TO_TYPE = "billto";
    public static final String SHIP_TO_TYPE = "shipto";

    /**
     * STIBO Server Configurations
     */
    public static final String HOST = SUTAPProperties.getProperty("stibo.host");
    public static final String USER = SUTAPProperties.getProperty("stibo.user");
    public static final String PASSWORD = SUTAPProperties.getProperty("stibo.password");
    public static final String REMOTE_DIR_PATH = SUTAPProperties.getProperty("stibo.remoteDirPath");
    public static final String REMOTE_DIR_PATH_SHIP_TO = SUTAPProperties.getProperty("stibo.remoteDirPathShipTo");
    public static final String AUTH_KEY_REQUEST = SUTAPProperties.getProperty("stibo.authKeyRequest");
    public static final String AUTH_KEY = SUTAPProperties.getProperty("stibo.authKey");


    /**
     * Entity Reference Types
     */
    public static final String CUST_BILL_TO_GR_BILL_TO = "scdlab_cust_bill_to_gr_bill_to";

    /**
     * Update Data
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * S3 Bucket Location Details
     */
    public static final String BUCKET_NAME = "cmdm-outbound-incremental-data";
    public static final String FOLDER_KEY = "billto/2022/8/12/08-12-2022-11:36:55.zip";


    /**
     * Zip File Downloaded Location Details
     */
    public static final String ZIP_FILE_LOCATION = "C:\\Sysco\\test.zip";


    /**
     * Record from STIBO
     */
    public static final String BILL_TO_SITE_SHIP_TO = "SCDLab_BillTo-9225477";
    public static final String SHIP_TO_SITE_SHIP_TO = "SCDLab-ShipTo-9199542";
}
