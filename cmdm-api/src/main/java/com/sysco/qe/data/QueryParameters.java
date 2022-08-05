package com.sysco.qe.data;

import java.util.HashMap;
import java.util.Map;

public class QueryParameters {

    private QueryParameters() {
    }

    public static Map<String, String> getQueryParameters(){
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("context", "Context5");
        queryParams.put("workspace", "Main");
        return queryParams;
    }

}
