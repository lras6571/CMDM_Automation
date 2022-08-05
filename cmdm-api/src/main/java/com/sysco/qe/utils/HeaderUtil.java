package com.sysco.qe.utils;

import java.util.HashMap;
import java.util.Map;

import static com.sysco.qe.data.ApiHeaderValues.*;

/**
 * @apiNote Handling Header Utils
 * @since 2022/07/21
 */
public class HeaderUtil {

    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT_ENCODING_ENTITY = "gzip, deflate, br";

    /**
     * Header Values
     *
     * @return headers
     */
    public static Map<String, String> getRequestHeaders(String key) {

        Map<String, String> headers = new HashMap<>();
        headers.put(CONTENT_TYPE, APPLICATION_JSON);
        headers.put(ACCEPT, APPLICATION_JSON);
        headers.put(ACCEPT_ENCODING, ACCEPT_ENCODING_ENTITY);
        headers.put(AUTHORIZATION, "Basic " + key);

        return headers;

    }

}
