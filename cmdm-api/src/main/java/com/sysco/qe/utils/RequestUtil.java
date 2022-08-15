package com.sysco.qe.utils;

import com.sysco.qe.data.URIs;
import com.sysco.qe.response.model.EntitySearchDetailsResponse;
import com.sysco.qe.response.model.EntitySearchResponse;
import com.sysco.qe.response.model.ValueDetails;
import com.sysco.qeutils.utils.APICommonUtil;
import com.sysco.qeutils.utils.RequestMethods;
import com.sysco.qeutils.utils.ResponseUtils;
import com.syscolab.qe.core.api.restassured.RestUtil;
import io.restassured.response.Response;

import java.util.Map;

import static com.sysco.qe.common.APIConstants.*;

public class RequestUtil {

    private RequestUtil(){

    }
    public static EntitySearchResponse getEntitySearchResponse(String apiRequest, Map<String, String> queryParams) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_ENTITIES_SEARCH;
        Response response = RestUtil.send(HeaderUtil.getRequestHeaders("Y2lkZDUzMzA6Y2hpcmFudGhh"), apiRequest, uri, RequestMethods.REQ_METHOD_POST, queryParams);
        EntitySearchResponse entitySearchResponse = (EntitySearchResponse) ResponseUtils.getResponseAsObject(response.asString().replace("[", "").replace("]", ""), EntitySearchResponse.class);
        entitySearchResponse.setStatusCode(response.getStatusCode());

        return entitySearchResponse;

    }

    public static Response getEntitySearchResponseWithEmptyBody(String apiRequest, Map<String, String> queryParams) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_ENTITIES_SEARCH;
        Response response = RestUtil.send(HeaderUtil.getRequestHeaders("Y2lkZDUzMzA6Y2hpcmFudGhh"), apiRequest, uri, RequestMethods.REQ_METHOD_POST, queryParams);

        return response;

    }

    public static EntitySearchDetailsResponse getEntityDetailsResponse(Map<String, String> queryParams, String accountId) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_ENTITIES;
        Response response = RestUtil.send(HeaderUtil.getRequestHeaders("Y2lkZDUzMzA6Y2hpcmFudGhh"), "", uri.replace("{accountId}", accountId), RequestMethods.REQ_METHOD_GET, queryParams);
        EntitySearchDetailsResponse entitySearchDetailsResponse = (EntitySearchDetailsResponse) ResponseUtils.getResponseAsObject(response.asString(), EntitySearchDetailsResponse.class);
        entitySearchDetailsResponse.setStatusCode(response.getStatusCode());

        return entitySearchDetailsResponse;

    }

    public static int deleteEntityReference(Map<String, String> queryParams, String enterpriseId, String refTypeId, String targetId) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_ENTITIES_REFERENCE;
        Response response = RestUtil.send(HeaderUtil.getRequestHeaders("Y2lkZDUzMzA6Y2hpcmFudGhh"), "",
                uri.replace("{id}", enterpriseId).replace("{referenceTypeId}", refTypeId).replace("{targetId}", targetId), RequestMethods.REQ_METHOD_DELETE, queryParams);

        return response.getStatusCode();

    }

    public static int deleteEntity(Map<String, String> queryParams, String entityId) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_ENTITIES;
        Response response = RestUtil.send(HeaderUtil.getRequestHeaders("Y2lkZDUzMzA6Y2hpcmFudGhh"), "", uri.replace("{accountId}", entityId), RequestMethods.REQ_METHOD_DELETE, queryParams);

        return response.getStatusCode();

    }


    //Change Entity Record Value
    public static ValueDetails changeEntityRecordValue(String apiRequest, Map<String, String> queryParams,String accountId,String type) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_ENTITIES_VALUES;
        Response response = RestUtil.send(HeaderUtil.getRequestHeaders("bHJhczY1NzE6bGFzYW4="), apiRequest, uri.replace("{accountId}", accountId).replace("{type}", type), RequestMethods.REQ_METHOD_PUT, queryParams);
        return (ValueDetails) ResponseUtils.getResponseAsObject(response.asString(), ValueDetails.class);
    }


    //Approve the Object
    public static Response approveObject(String apiRequest, Map<String, String> queryParams,String accountId) {

        APICommonUtil.setProtocolHostAndBasePath(BASE_HTTPS_PROTOCOL, BASE_DOMAIN_STIBO, BASE_PATH_STIBO);
        String uri = URIs.URI_STIBO_OBJECT_APPROVAL;
        return RestUtil.send(HeaderUtil.getRequestHeaders("bHJhczY1NzE6bGFzYW4="), apiRequest, uri.replace("{accountId}", accountId), RequestMethods.REQ_METHOD_POST, queryParams);

    }

}
