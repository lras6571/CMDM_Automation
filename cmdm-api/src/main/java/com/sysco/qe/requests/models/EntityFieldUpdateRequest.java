package com.sysco.qe.requests.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EntityFieldUpdateRequest implements Serializable {

    private Value value;

    @Getter
    @Setter
    public static class Value {
        private String value;


    }

}
