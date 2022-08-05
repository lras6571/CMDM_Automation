package com.sysco.qe.response.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class BaseResponse implements Serializable {

    protected int statusCode;

}
