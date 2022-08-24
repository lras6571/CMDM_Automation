package com.sysco.qe.utils;

import com.sysco.qe.common.APIConstants;
import com.sysco.qe.data.APIAssertErrorMessages;
import com.sysco.qeutils.utils.DateUtils;

import java.util.Map;

import static com.sysco.qe.data.BillToMappingCodes.*;
import static com.sysco.qe.data.ShipToMappingCodes.*;

public class AssertionUtils extends APITestBase {

    public static void assertBillToEntityDetails(Map<String, String> csvData, String completenessStatus) {

        softAssert.assertEquals(siteBillToResponse.getId(), entitySearchResponse.getId(), APIAssertErrorMessages.INVALID_ID);
        softAssert.assertEquals(siteBillToResponse.getName(), csvData.get(APIConstants.BILL_TO_NAME), APIAssertErrorMessages.INVALID_ACC_NAME);
        softAssert.assertEquals(siteBillToResponse.getObjectType(), APIConstants.SCDLAB_CUST_BILLTO, APIAssertErrorMessages.INVALID_OBJ_TYPE);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_sbilltoaccountnumber().getValue().getValue(), csvData.get(APIConstants.BILL_TO_NUMBER), APIAssertErrorMessages.INVALID_BILL_TO_ACC_NO);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_billtostatus().getValue().getValueId(), csvData.get(APIConstants.STATUS), APIAssertErrorMessages.INVALID_BILL_TO_STATUS);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_billtocity().getValue().getValue(), csvData.get(APIConstants.BILL_TO_CITY), APIAssertErrorMessages.INVALID_BILL_TO_CITY);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_billtoaccountname().getValue().getValue(), csvData.get(APIConstants.BILL_TO_NAME), APIAssertErrorMessages.INVALID_BILL_TO_ACC_NAME);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_creationdate().getValue().getValue(), DateUtils.getCurrentDate("dd-MMM-yyyy"), APIAssertErrorMessages.INVALID_BILL_TO_ACC_CREATION_DATE);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_billtozipcode().getValue().getValue(), csvData.get(APIConstants.BILL_TO_POSTALCODE), APIAssertErrorMessages.INVALID_BILL_TO_ZIP);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_billtocountrycode().getValue().getValue(), csvData.get(APIConstants.BILL_TO_COUNTRY), APIAssertErrorMessages.INVALID_BILL_TO_COUNTRY);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_sbilltotelephonenumber().getValue().getValue(), csvData.get(APIConstants.PHONE), APIAssertErrorMessages.INVALID_BILL_TO_TEL);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_sbcompletenessstatus().getValue().getValue(), completenessStatus, APIAssertErrorMessages.INVALID_BILL_TO_COMPLETE_STATUS);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_customertype().getValue().getValueId(), csvData.get(APIConstants.CUSTOMER_TYPE), APIAssertErrorMessages.INVALID_BILL_TO_CUST_TYPE);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_billtostatecode().getValue().getValue(), csvData.get(APIConstants.BILL_TO_STATE), APIAssertErrorMessages.INVALID_BILL_TO_STATE);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_sourcesystem().getValue().getValue(), csvData.get(APIConstants.SOURCE_SYSTEM), APIAssertErrorMessages.INVALID_BILL_TO_SOURCE);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_sbilltoopcoid().getValue().getValue(), csvData.get(APIConstants.OPCO_NUMBER), APIAssertErrorMessages.INVALID_BILL_TO_OPCO);
        softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_gr_creationdate().getValue().getValue().split(" ")[0], DateUtils.getCurrentDate("yyyy-MM-dd"), APIAssertErrorMessages.INVALID_BILL_TO_GR_ACC_CREATION_DATE);

        if (siteBillToResponse.getValues().getScdlab_cust_a_billtoaddressline1() != null) {
            softAssert.assertEquals(siteBillToResponse.getValues().getScdlab_cust_a_billtoaddressline1().getValue().getValue(), csvData.get(APIConstants.BILL_TO_ADDRESS_1), APIAssertErrorMessages.INVALID_BILL_TO_ADDRESS1);
        }

    }

    public static void assertBillToWorkflowError(String expectedErrorMsg) {

        softAssert.assertEquals(siteBillToResponse.getValues().getSyy_cust_a_workflowerrors().getValue().getValue(), expectedErrorMsg, APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);

    }

    public static void assertBillToCSV(String filePath) {

        softAssert.assertEquals(ZipFileReaderUtil.readBillToNameFromFile(BILL_TO_ACCOUNT_NAME_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_NAME_CSV, filePath), APIAssertErrorMessages.INVALID_ACC_NAME);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToAccountNumberFromFile(BILL_TO_ACCOUNT_NUMBER_S3), DataFieldRetrieveUtil.retrieveData(BILLTO_NUMBER_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_ACC_NO);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToStatusFromFile(BILL_TO_ACCOUNT_STATUS_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_STATUS_CSV, filePath), APIAssertErrorMessages.INVALID_STATUS_CODE);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToAddressFromFile(BILL_TO_ADDRESS_LINE1_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_ADDRESS_1_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_ADDRESS1);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToCityFromFile(BILL_TO_CITY_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_CITY_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_CITY);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToStateFromFile(BILL_TO_STATE_CODE_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_STATE_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_STATE);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToTelephoneNumberFromFile(BILL_TO_TELEPHONE_NUMBER_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_PHONE_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_TEL);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToPostalCodeFromFile(BILL_TO_ZIP_CODE_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_POSTAL_CODE_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_ZIP);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToCountryFromFile(BILL_TO_COUNTRY_CODE_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_COUNTRY_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_COUNTRY);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToOpCoIDFromFile(BILL_TO_OPCO_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_OPCO_NUMBER_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_OPCO);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToSourceSystemFromFile(BILL_TO_SOURCE_SYSTEM_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_SOURCE_SYSTEM_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_SOURCE);

        softAssert.assertAll();
    }


    //Assert Ship to Fields
    public static void assertShipToCSV(String filePath) {

        softAssert.assertEquals(ZipFileReaderUtil.readShipToAccountNameFromFile(SHIP_TO_SHIP_TO_ACCOUNT_NAME_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_NAME_CSV, filePath), APIAssertErrorMessages.INVALID_ACC_NAME);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToAccountNumberFromFile(SHIP_TO_SCD_BILL_TO_ACCOUNT_NUMBER_S3), DataFieldRetrieveUtil.retrieveData(BILL_TO_NUMBER_CSV, filePath), APIAssertErrorMessages.INVALID_BILL_TO_ACC_NO);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToAccountStatusFromFile(SHIP_TO_STATUS_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_STATUS_CSV, filePath), APIAssertErrorMessages.INVALID_STATUS_CODE);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToAddressFromFile(SHIP_TO_ADDRESS_LINE1_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_ADDRESS_1_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_ADDRESS1);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToCityFromFile(SHIP_TO_CITY_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_CITY_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_CITY);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToStateFromFile(SHIP_TO_STATE_CODE_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_STATE_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_STATE);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToTelephoneNumberFromFile(SHIP_TO_TELEPHONE_NUMBER_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_PHONE_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_TEL);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToPostalCodeFromFile(SHIP_TO_ZIP_CODE_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_POSTAL_CODE_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_ZIP);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToCountryFromFile(SHIP_TO_COUNTRY_CODE_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_COUNTRY_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_COUNTRY);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToOpCoIDFromFile(SHIP_TO_OPCO_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_OPCO_NUMBER_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_OPCO);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToSourceSystemFromFile(SHIP_TO_SOURCE_SYSTEM_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_SOURCE_SYSTEM_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_SOURCE);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToStoreNumberFromFile(SHIP_TO_STORE_NUMBER_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_STORE_NUMBER_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_STORE);
        softAssert.assertEquals(ZipFileReaderUtil.readShipToAccountNumberFromFile(SHIP_TO_ENTERPRISE_BILL_TO_FOR_SITE_SHIP_TO_S3), DataFieldRetrieveUtil.retrieveData(SHIP_TO_NUMBER_CSV, filePath), APIAssertErrorMessages.INVALID_SHIP_TO_ENTERPRISE_BILL_TO_FOR_SITE_SHIP_TO);

        softAssert.assertAll();
    }


    //Assert Bill to Fields
    public static void assertShipToVendorNumberCSV(String VendorNumber) {

        softAssert.assertEquals(ZipFileReaderUtil.readShipToVendorNumberFromFile(SHIP_TO_VENDOR_NUMBER_S3), VendorNumber, APIAssertErrorMessages.INVALID_VENDOR_NAME);
        softAssert.assertAll();
    }

    public static void assertBillToVendorNumberCSV(String VendorNumber) {

        softAssert.assertEquals(ZipFileReaderUtil.readBillToVendorNumberFromFile(BILL_TO_VENDOR_NUMBER_S3), VendorNumber, APIAssertErrorMessages.INVALID_VENDOR_NAME);
        softAssert.assertAll();
    }
}
