package com.sysco.qe.data;

public class URIs {

    URIs() {
    }
    //End Points
    public static final String URI_STIBO_ENTITIES_SEARCH = "entities/search";
    public static final String URI_STIBO_ENTITIES = "entities/{accountId}";
    public static final String URI_STIBO_ENTITIES_VALUES1 = "entities/{accountId}/values/scdlab_cust_a_billtovendornumber";
    public static final String URI_STIBO_ENTITIES_VALUES = "entities/{accountId}/values/scdlab_cust_a_{type}vendornumber";
    public static final String URI_STIBO_OBJECT_APPROVAL = "entities/{accountId}/approve";
    public static final String URI_STIBO_ENTITIES_REFERENCE = "entities/{id}/references/{referenceTypeId}/{targetId}";

    /*
     * QCenter
     * */
    public static final String URI_QCENTER_PROJECTS = "projects/name/{text}";
    public static final String URI_QCENTER_ENVS = "environments/project/{text}";
    public static final String URI_QCENTER_BUILDS_BY_PROJ_ENV = "builds/project/{project}/environment/{env}";
    public static final String URI_QCENTER_BUILD_INFO = "builds/{text}";



}
