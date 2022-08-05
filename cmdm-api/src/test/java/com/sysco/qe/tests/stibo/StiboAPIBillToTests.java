package com.sysco.qe.tests.stibo;

import com.sysco.qe.data.APIAssertErrorMessages;
import com.sysco.qe.data.APIStatusCodes;
import com.sysco.qe.data.QueryParameters;
import com.sysco.qe.utils.*;
import com.sysco.qeutils.utils.JacksonUtil;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.sysco.qe.common.APIConstants.*;

public class StiboAPIBillToTests extends APITestBase {

    @BeforeClass
    public void init(ITestContext iTestContext) {

//        iTestContext.setAttribute(FEATURE_QCENTER, MODULE_API_CLOUD_PCI_PVT + " - " + FEATURE_API_PRODUCT_PRICES);
        RemoteServerUtil.uploadFileToRemoteServer(HOST, USER, PASSWORD, REMOTE_DIR_PATH, INPUT_BILL_TO_DATA_CSV_FILE);
        csvDataList = DataUtil.readCSVAsListOfMaps(INPUT_BILL_TO_DATA_CSV_FILE);

    }

    /**
     * Verify Site Bill To records
     */
    @Test(description = "CDI-TC-6", alwaysRun = true, priority = 1)
    public void testVerifySiteBillToRecords() {

        entitySearchRequest.getCondition().getConditions().get(0).setQueryString(csvDataList.get(0).get(BILL_TO_NAME));

        entitySearchResponse = RequestUtil.getEntitySearchResponse(JacksonUtil.convertObjectToJsonString(entitySearchRequest), QueryParameters.getQueryParameters());
        softAssert.assertEquals(entitySearchResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

        siteBillToResponse = RequestUtil.getEntityDetailsResponse(QueryParameters.getQueryParameters(), entitySearchResponse.getId());
        softAssert.assertEquals(siteBillToResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);
        AssertionUtils.assertBillToEntityDetails(csvDataList.get(0), COMPLETED);

        IS_DATA_INJECTED = Boolean.parseBoolean(TRUE);

        softAssert.assertAll();

    }

    /**
     * Verify Site Bill To Account Name
     */
    @Test(description = "CDI-TC-7", alwaysRun = true, priority = 2)
    public void testVerifyBillToAccountName() {

        entitySearchRequest.getCondition().getConditions().get(0).setQueryString(csvDataList.get(1).get(BILL_TO_NAME));

        Response entitySearchResponse = RequestUtil.getEntitySearchResponseWithEmptyBody(JacksonUtil.convertObjectToJsonString(entitySearchRequest), QueryParameters.getQueryParameters());
        softAssert.assertEquals(entitySearchResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);
        softAssert.assertEquals(entitySearchResponse.getBody().asString(), "[]", APIAssertErrorMessages.INVALID_BILL_TO_RESPONSE_BODY);

        IS_DATA_INJECTED = Boolean.parseBoolean(FALSE);

        softAssert.assertAll();

    }

    /**
     * Verify Site Bill To Account Number
     */
    @Test(description = "CDI-TC-8", alwaysRun = true, priority = 3)
    public void testVerifySiteBillToAccountNo() {

        entitySearchRequest.getCondition().getConditions().get(0).setQueryString(csvDataList.get(2).get(BILL_TO_NAME));

        Response entitySearchResponse = RequestUtil.getEntitySearchResponseWithEmptyBody(JacksonUtil.convertObjectToJsonString(entitySearchRequest), QueryParameters.getQueryParameters());
        softAssert.assertEquals(entitySearchResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);
        softAssert.assertEquals(entitySearchResponse.getBody().asString(), "[]", APIAssertErrorMessages.INVALID_BILL_TO_RESPONSE_BODY);

        IS_DATA_INJECTED = Boolean.parseBoolean(FALSE);

        softAssert.assertAll();

    }

    /**
     * Verify Site Bill To Account Address
     */
    @Test(description = "CDI-TC-9", alwaysRun = true, priority = 4)
    public void testVerifySiteBillToAccountAddress() {

        entitySearchRequest.getCondition().getConditions().get(0).setQueryString(csvDataList.get(3).get(BILL_TO_NAME));

        entitySearchResponse = RequestUtil.getEntitySearchResponse(JacksonUtil.convertObjectToJsonString(entitySearchRequest), QueryParameters.getQueryParameters());
        softAssert.assertEquals(entitySearchResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

        siteBillToResponse = RequestUtil.getEntityDetailsResponse(QueryParameters.getQueryParameters(), entitySearchResponse.getId());
        softAssert.assertEquals(siteBillToResponse.getStatusCode(), APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);
        AssertionUtils.assertBillToEntityDetails(csvDataList.get(3), NOT_COMPLETED);
        AssertionUtils.assertBillToWorkflowError("SCDLab Bill To Address Line 1");

        IS_DATA_INJECTED = Boolean.parseBoolean(FALSE);

        softAssert.assertAll();

    }

}
