package com.sysco.qe.tests.kafkaconsumer;

import com.sysco.qe.data.APIAssertErrorMessages;
import com.sysco.qe.data.APIStatusCodes;
import com.sysco.qe.data.QueryParameters;
import com.sysco.qe.response.model.ValueDetails;
import com.sysco.qe.utils.*;
import com.sysco.qeutils.utils.JacksonUtil;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.sysco.qe.common.APIConstants.*;

public class KafkaConsumerTest extends APITestBase {

    @BeforeClass
    public void init(ITestContext iTestContext) {

//        iTestContext.setAttribute(FEATURE_QCENTER, MODULE_API_CLOUD_PCI_PVT + " - " + FEATURE_API_PRODUCT_PRICES);
        RemoteServerUtil.uploadFileToRemoteServer(HOST, USER, PASSWORD, REMOTE_DIR_PATH, INPUT_BILL_TO_DATA_CSV_FILE2);
        csvDataList = DataUtil.readCSVAsListOfMaps(INPUT_BILL_TO_DATA_CSV_FILE2);

    }

    /**
     * Download File from S3 Bucket and Read
     */
    @Test(description = "CDI-TC-100", alwaysRun = true, priority = 1)
    public void testDownloadFromS3Bucket() throws IOException {

        AWSS3Util.getS3File(BUCKET_NAME, FOLDER_KEY);

        //Read Downloaded Zip File
        FileInputStream input = new FileInputStream(new File(ZIP_FILE_LOCATION));
        ZipFileReaderUtil.readZip(input, ZIP_FILE_LOCATION);

        //Update the field Value
        entitySearchRequest.getCondition().getConditions().get(0).setQueryString(csvDataList.get(0).get(BILL_TO_NAME));
        ValueDetails valueDetails = RequestUtil.changeEntityRecordValue(JacksonUtil.convertObjectToJsonString(entityFieldUpdateRequest), QueryParameters.getQueryParameters(),BILL_TO_SITE_SHIP_TO);
        softAssert.assertEquals(valueDetails.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

    }
}