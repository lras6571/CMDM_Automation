package com.sysco.qe.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntitySearchResponse extends BaseResponse {

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;

}
