package com.sysco.qe.tests.kafkaconsumer;

import com.sysco.qe.utils.*;
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
     * Download File from S3 Bucket
     */
    @Test(description = "CDI-TC-xx", alwaysRun = true, priority = 1)
    public void testDownloadFromS3Bucket() throws IOException {

        AWSS3Util.getS3File(BUCKET_NAME,FOLDER_KEY);
    }


    /**
     * Read Downloaded Zip File
     */
    @Test(description = "CDI-TC-yy", alwaysRun = true, priority = 1)
    public void testReadDownloadedZipFile() throws IOException {

        String name = "C:\\Sysco\\test.zip";
        FileInputStream input = new FileInputStream(new File(name));
        ZipFileReaderUtil.readZip(input, name);
    }
}
