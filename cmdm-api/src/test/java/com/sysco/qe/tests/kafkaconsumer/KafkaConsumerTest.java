package com.sysco.qe.tests.kafkaconsumer;

import com.sysco.qe.data.APIAssertErrorMessages;
import com.sysco.qe.data.APIStatusCodes;
import com.sysco.qe.data.QueryParameters;
import com.sysco.qe.utils.*;
import com.sysco.qeutils.utils.JacksonUtil;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.sysco.qe.common.APIConstants.*;

public class KafkaConsumerTest extends APITestBase {
//
//    @BeforeClass
//    public void init(ITestContext iTestContext) {
//
////        iTestContext.setAttribute(FEATURE_QCENTER, MODULE_API_CLOUD_PCI_PVT + " - " + FEATURE_API_PRODUCT_PRICES);
//        RemoteServerUtil.uploadFileToRemoteServer(HOST, USER, PASSWORD, REMOTE_DIR_PATH, INPUT_BILL_TO_DATA_CSV_FILE2);
//        csvDataList = DataUtil.readCSVAsListOfMaps(INPUT_BILL_TO_DATA_CSV_FILE2);
//
//    }

//    /**
//     * Download File from S3 Bucket and Read - Bill To
//     */
//    @Test(description = "CDI-TC-100", alwaysRun = true, priority = 1)
//    public void testUploadFileToS3BillTo() throws IOException, InterruptedException {
//
//        RemoteServerUtil.uploadFileToRemoteServer(HOST, USER, PASSWORD, REMOTE_DIR_PATH, INPUT_BILL_TO_DATA_CSV_FILE2);
//        csvDataList = DataUtil.readCSVAsListOfMaps(INPUT_BILL_TO_DATA_CSV_FILE2);
//
//        //Retrieve the S3 Key and Pick the Correct File, Then Read Downloaded Zip File
//        AWSS3Util.getS3File(BUCKET_NAME, FolderKeyUtil.retrieveKey(BILL_TO_TYPE));
//        FileInputStream input = new FileInputStream(new File(ZIP_FILE_LOCATION));
//        ZipFileReaderUtil.readZip(input, ZIP_FILE_LOCATION);
//        AssertionUtils.assertBillToCSV(INPUT_BILL_TO_DATA_CSV_FILE2);
//    }
//
//
//    /**
//     * Download File from S3 Bucket and Read - Ship To
//     */
//    @Test(description = "CDI-TC-114", alwaysRun = true, priority = 1)
//    public void testUploadFileToS3ShipTo() throws IOException, InterruptedException {
//
//        RemoteServerUtil.uploadFileToRemoteServer(HOST, USER, PASSWORD, REMOTE_DIR_PATH_SHIP_TO, INPUT_SHIP_TO_DATA_CSV_FILE3);
//        csvDataList = DataUtil.readCSVAsListOfMaps(INPUT_SHIP_TO_DATA_CSV_FILE3);
//
//        //Retrieve the S3 Key and Pick the Correct File, Then Read Downloaded Zip File
//        AWSS3Util.getS3File(BUCKET_NAME, FolderKeyUtil.retrieveKey(SHIP_TO_TYPE));
//        FileInputStream input = new FileInputStream(new File(ZIP_FILE_LOCATION));
//        ZipFileReaderUtil.readZip(input, ZIP_FILE_LOCATION);
//        AssertionUtils.assertShipToCSV(INPUT_SHIP_TO_DATA_CSV_FILE3);
//    }
//
//
//
    /**
     * Object Approval and Verify the Record is available in S3 Bucket - Bill To
     */
    @Test(description = "CDI-TC-147", alwaysRun = true, priority = 1)
    public void testObjectApprovalBillToTest() throws IOException, InterruptedException {

        //Update the field Value of the Object
        String randomValue = RandomStringGenerateUtil.randomStringGenerator();
        entityFieldUpdateRequest.getValue().setValue(randomValue);
        valueDetails = RequestUtil.changeEntityRecordValue(JacksonUtil.convertObjectToJsonString(entityFieldUpdateRequest), QueryParameters.getQueryParameters(), BILL_TO_SITE_SHIP_TO, BILL_TO_TYPE, AUTH_KEY_REQUEST);
        softAssert.assertEquals(valueDetails.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

        //Approve the Object
        Response approvalResponse = RequestUtil.approveObject(JacksonUtil.convertObjectToJsonString(approveObjectRequest), QueryParameters.getQueryParameters(), BILL_TO_SITE_SHIP_TO, AUTH_KEY_REQUEST);
        softAssert.assertEquals(approvalResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

        //Retrieve the S3 Key and Pick the Correct File
        AWSS3Util.getS3File(BUCKET_NAME, FolderKeyUtil.retrieveKey(BILL_TO_TYPE));

        //Read Downloaded Zip File
        FileInputStream input = new FileInputStream(new File(ZIP_FILE_LOCATION));
        ZipFileReaderUtil.readZip(input, ZIP_FILE_LOCATION);
        String VendorNumber = RequestUtil.retrieveBillToRecordDetailsResponse(QueryParameters.getQueryParameters(), BILL_TO_SITE_SHIP_TO, AUTH_KEY_REQUEST);
        AssertionUtils.assertBillToVendorNumberCSV(VendorNumber);
        Assert.assertEquals(VendorNumber, randomValue);

    }


    /**
     * Object Approval and Verify the Record is available in S3 Bucket - Ship To
     */
    @Test(description = "CDI-TC-158", alwaysRun = true, priority = 2)
    public void testObjectApprovalShipToTest() throws IOException, InterruptedException {

        //Update the field Value of the Object Request
        String randomValue = RandomStringGenerateUtil.randomStringGenerator();
        entityFieldUpdateRequest.getValue().setValue(randomValue);
        valueDetails = RequestUtil.changeEntityRecordValue(JacksonUtil.convertObjectToJsonString(entityFieldUpdateRequest), QueryParameters.getQueryParameters(), SHIP_TO_SITE_SHIP_TO, SHIP_TO_TYPE, AUTH_KEY_REQUEST);
        softAssert.assertEquals(valueDetails.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

        //Approve the Object Request
        Response approvalResponse = RequestUtil.approveObject(JacksonUtil.convertObjectToJsonString(approveObjectRequest), QueryParameters.getQueryParameters(), SHIP_TO_SITE_SHIP_TO, AUTH_KEY_REQUEST);
        softAssert.assertEquals(approvalResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

        //Retrieve the S3 Key and Pick the Correct File, Then Read Downloaded Zip File
        AWSS3Util.getS3File(BUCKET_NAME, FolderKeyUtil.retrieveKey(SHIP_TO_TYPE));
        FileInputStream input = new FileInputStream(new File(ZIP_FILE_LOCATION));
        ZipFileReaderUtil.readZip(input, ZIP_FILE_LOCATION);
        String VendorNumber = RequestUtil.retrieveShipToRecordDetailsResponse(QueryParameters.getQueryParameters(), SHIP_TO_SITE_SHIP_TO, AUTH_KEY_REQUEST);
        AssertionUtils.assertShipToVendorNumberCSV(VendorNumber);
        Assert.assertEquals(VendorNumber, randomValue);
    }
}