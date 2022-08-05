package com.sysco.qe.requests.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EntitySearchRequest implements Serializable {

    private Condition condition;

    @Getter
    @Setter
    public static class Condition {
        private String conditionType;
        private List<Conditions> conditions;

        @Getter
        @Setter
        public static class Conditions {
            private String conditionType;
            private String operator;
            private String queryString;
            private String objectType;
        }

    }

}
