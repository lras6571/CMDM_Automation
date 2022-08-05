package com.sysco.qe.response.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StatusResponse implements Serializable {
    private Integer code;
    private String state;
    private String message;
}
