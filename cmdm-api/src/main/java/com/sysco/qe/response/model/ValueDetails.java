package com.sysco.qe.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueDetails extends EntitySearchResponse {

    @JsonProperty("calculated")
    private String calculated;

    @JsonProperty("contextLocal")
    private String contextLocal;

    @JsonProperty("value")
    private Value value;

    @Getter
    @Setter
    public static class Value {

        @JsonProperty("value")
        private String value;

        @JsonProperty("valueId")
        private String valueId;

        @JsonProperty("unit")
        private String unit;

    }

}
