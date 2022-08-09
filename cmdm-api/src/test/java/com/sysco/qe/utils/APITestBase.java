package com.sysco.qe.utils;

import com.sysco.qe.common.APIConstants;
import com.sysco.qe.data.APIAssertErrorMessages;
import com.sysco.qe.data.APIStatusCodes;
import com.sysco.qe.data.QueryParameters;
import com.sysco.qe.requests.models.EntityFieldUpdateRequest;
import com.sysco.qe.requests.models.EntitySearchRequest;
import com.sysco.qe.response.model.EntitySearchDetailsResponse;
import com.sysco.qe.response.model.EntitySearchResponse;
import com.sysco.qe.response.model.ValueDetails;
import com.syscolab.qe.core.common.LoggerUtil;
import com.syscolab.qe.core.reporting.SyscoLabListener;
import com.syscolab.qe.core.reporting.SyscoLabQCenter;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.sysco.qe.common.APIConstants.*;

@Listeners(SyscoLabListener.class)
public class APITestBase {

    protected static SoftAssert softAssert;
    protected static EntitySearchRequest entitySearchRequest;
    protected static EntityFieldUpdateRequest entityFieldUpdateRequest;
    protected static EntitySearchResponse entitySearchResponse;
    protected static EntitySearchDetailsResponse siteBillToResponse;
    protected static ValueDetails valueDetails;
    protected static EntitySearchDetailsResponse enterpriseBillToResponse;
    protected static List<Map<String, String>> csvDataList;
    protected SyscoLabQCenter syscoLabQCenter;
    private SyscoLabListener testListeners;
    private String testCaseID = null;
    private String failedTestCaseId = null;
    private String testCaseStatus = null;

    public static boolean IS_DATA_INJECTED = Boolean.parseBoolean(FALSE);

    public static void initializeReportData() {
//        System.setProperty("test.env", APIConstants.TEST_ENV);
//        System.setProperty("test.release", APIConstants.TEST_RELEASE);
//        System.setProperty("test.project", APIConstants.TEST_PROJECT);
//        System.setProperty("update.dashboard", String.valueOf(APIConstants.UPDATE_DASHBOARD));
    }

    public void generateBuild() {
//        try {
//            if (APIConstants.IS_CREATE_BUILD_ENABLED) {
//                customReleaseName = APIConstants.TEST_RELEASE + "_" + DateUtils.getQCenterBuildDate();
//                LoggerUtil.logINFO("Custom Build Name : " + customReleaseName);
//                qcenterBuildInfo = APICommonFunctions.getBuildInfoByName(APICommonFunctions.getQCenterBuildList(), customReleaseName);
//                LoggerUtil.logINFO("Build Info : " + qcenterBuildInfo);
//                if (qcenterBuildInfo.isEmpty()) {
//                    LoggerUtil.logINFO("Generating Build");
//                    QCenterUtil.generateBuild(APIConstants.TEST_PROJECT, APIConstants.TEST_ENV, customReleaseName);
//                } else {
//                    LoggerUtil.logINFO("Build already exists and will not be created");
//                }
//            }
//        } catch (Exception ex) {
//            LoggerUtil.logERROR(ex.getMessage(), ex);
//        }
    }

    public void init() {
        LoggerUtil.logINFO("Test Running " + this.getClass().toString());
        entitySearchRequest = (EntitySearchRequest) DataUtil.loadTestData(APIConstants.INPUT_DATA_JSON_FILE, EntitySearchRequest.class);
        entityFieldUpdateRequest = (EntityFieldUpdateRequest) DataUtil.loadTestData(APIConstants.INPUT_DATA_JSON_FILE2, EntityFieldUpdateRequest.class);
    }

    @BeforeSuite
    public void runBeforeSuite() {
        initializeReportData();
        generateBuild();
        init();
    }

    @BeforeMethod(alwaysRun = true)
    public void initQCenter() {
        testListeners = new SyscoLabListener();
        syscoLabQCenter = new SyscoLabQCenter();
    }

    @BeforeMethod(alwaysRun = true)
    public void initMethod() {
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void updateDashboard(ITestContext iTestContext) {
//        if (APIConstants.UPDATE_DASHBOARD) {
//            try {
//                syscoLabQCenter.setProjectName(APIConstants.TEST_PROJECT);
//                syscoLabQCenter.setEnvironment(APIConstants.TEST_ENV);
//
//                if (qcenterBuildInfo == null) {
//                    syscoLabQCenter.setRelease(APIConstants.TEST_RELEASE);
//                } else {
//                    syscoLabQCenter.setRelease(customReleaseName);
//                }
//
//                syscoLabQCenter.setModule(iTestContext.getAttribute("feature").toString());
//                syscoLabQCenter.setFeature(iTestContext.getAttribute("feature").toString());
//                syscoLabQCenter.setClassName(iTestContext.getClass().getName());
//
//                testCaseID = SyscoLabListener.getResults().get(0).getAsJsonObject().get("id").getAsString();
//                testCaseStatus = SyscoLabListener.getResults().get(0).getAsJsonObject().get("steps").getAsJsonArray().get(0).getAsJsonObject().get("result").getAsJsonObject().get("status").getAsString();
//                JsonObject resultsObject = SyscoLabListener.getResults().get(0).getAsJsonObject();
//                JsonArray resultsArray = new JsonArray();
//                resultsArray.add(resultsObject);
//                LoggerUtil.logINFO("Updating Dashboard...");
//
//                if (testCaseStatus.equals("failed")) {
//                    SyscoLabReporting.generateJsonFile(resultsArray, syscoLabQCenter);
//                    failedTestCaseId = testCaseID;
//                } else if (!testCaseID.equals(failedTestCaseId)) {
//                    SyscoLabReporting.generateJsonFile(resultsArray, syscoLabQCenter);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanInjectedTestData() throws SQLException {

        if (IS_DATA_INJECTED) {

            enterpriseBillToResponse = RequestUtil.getEntityDetailsResponse(QueryParameters.getQueryParameters(), siteBillToResponse.getParent());

            LoggerUtil.logINFO("Cleaning Data after the Test Execution");
            int entityRefDeleteStatus = RequestUtil.deleteEntityReference(QueryParameters.getQueryParameters(), siteBillToResponse.getParent(), CUST_BILL_TO_GR_BILL_TO, entitySearchResponse.getId());
            softAssert.assertEquals(entityRefDeleteStatus, APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

            int siteRecordDeleteStatus = RequestUtil.deleteEntity(QueryParameters.getQueryParameters(), entitySearchResponse.getId());
            softAssert.assertEquals(siteRecordDeleteStatus, APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

            int enterpriseRecordDeleteStatus = RequestUtil.deleteEntity(QueryParameters.getQueryParameters(), siteBillToResponse.getParent());
            softAssert.assertEquals(enterpriseRecordDeleteStatus, APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

            int custRecordDeleteStatus = RequestUtil.deleteEntity(QueryParameters.getQueryParameters(), enterpriseBillToResponse.getParent());
            softAssert.assertEquals(custRecordDeleteStatus, APIStatusCodes.RESPONSE_CODE_200, APIAssertErrorMessages.INVALID_STATUS_CODE);

            softAssert.assertAll();

        }

    }

}
