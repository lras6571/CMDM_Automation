package com.sysco.qe.data;

public class URIs {

    public static final String URI_STIBO_ENTITIES_SEARCH = "entities/search";
    public static final String URI_STIBO_ENTITIES = "entities/{accountId}";
//    public static final String URI_STIBO_ENTITIES_VALUES = "entities/{accountId}/values/scdlab_cust_a_billtovendornumber";
    public static final String URI_STIBO_ENTITIES_VALUES = "entities/SCDLab_BillTo-9225477/values/scdlab_cust_a_billtovendornumber";
    public static final String URI_STIBO_ENTITIES_REFERENCE = "entities/{id}/references/{referenceTypeId}/{targetId}";

    /*
     * QCenter
     * */
    public static final String URI_QCENTER_PROJECTS = "projects/name/{text}";
    public static final String URI_QCENTER_ENVS = "environments/project/{text}";
    public static final String URI_QCENTER_BUILDS_BY_PROJ_ENV = "builds/project/{project}/environment/{env}";
    public static final String URI_QCENTER_BUILD_INFO = "builds/{text}";

    URIs() {
    }

}
