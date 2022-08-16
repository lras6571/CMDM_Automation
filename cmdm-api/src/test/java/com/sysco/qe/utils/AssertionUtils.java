package com.sysco.qe.utils;

import com.sysco.qe.common.APIConstants;
import com.sysco.qe.data.APIAssertErrorMessages;
import com.sysco.qeutils.utils.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import static com.sysco.qe.common.APIConstants.ZIP_FILE_LOCATION;

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

    public static void assertCSV() throws IOException {

//        FileInputStream input = new FileInputStream(new File(ZIP_FILE_LOCATION));
        softAssert.assertEquals(ZipFileReaderUtil.readBillToNameFromFile(), DataFieldRetrieveUtil.retrieveData(2), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToAccountNumberFromFile(), DataFieldRetrieveUtil.retrieveData(1), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToStatusFromFile(), DataFieldRetrieveUtil.retrieveData(3), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToAddressFromFile(), DataFieldRetrieveUtil.retrieveData(4), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToCityFromFile(), DataFieldRetrieveUtil.retrieveData(6), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToStateFromFile(), DataFieldRetrieveUtil.retrieveData(7), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToTelephoneNumberFromFile(), DataFieldRetrieveUtil.retrieveData(8), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToPostalCodeFromFile(), DataFieldRetrieveUtil.retrieveData(9), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToCountryFromFile(), DataFieldRetrieveUtil.retrieveData(10), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readOpCoIDFromFile(),DataFieldRetrieveUtil.retrieveData(13), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);
        softAssert.assertEquals(ZipFileReaderUtil.readBillToSourceSystemFromFile(), DataFieldRetrieveUtil.retrieveData(14), APIAssertErrorMessages.INVALID_BILL_TO_WORKFLOW_ERROR);

        softAssert.assertAll();
    }

}
