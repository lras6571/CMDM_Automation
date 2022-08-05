package com.sysco.qe.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntitySearchDetailsResponse extends BaseResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("objectType")
    private String objectType;

    @JsonProperty("parent")
    private String parent;

    @JsonProperty("values")
    private Values values;

    @Getter
    @Setter
    public static class Values {

        @JsonProperty("scdlab_cust_a_sbilltoerrorMessageToggle")
        private ValueDetails scdlab_cust_a_sbilltoerrorMessageToggle;

        @JsonProperty("scdlab_cust_a_sbilltoaccountnumber")
        private ValueDetails scdlab_cust_a_sbilltoaccountnumber;

        @JsonProperty("scdlab_cust_a_gr_lastupdated")
        private ValueDetails scdlab_cust_a_gr_lastupdated;

        @JsonProperty("scdlab_cust_a_billtostatus")
        private ValueDetails scdlab_cust_a_billtostatus;

        @JsonProperty("scdlab_cust_a_billtocity")
        private ValueDetails scdlab_cust_a_billtocity;

        @JsonProperty("scdlab_cust_billtoaccountname")
        private ValueDetails scdlab_cust_billtoaccountname;

        @JsonProperty("scdlab_cust_a_creationdate")
        private ValueDetails scdlab_cust_a_creationdate;

        @JsonProperty("scdlab_cust_a_billtozipcode")
        private ValueDetails scdlab_cust_a_billtozipcode;

        @JsonProperty("scdlab_cust_a _billtocountrycode")
        private ValueDetails scdlab_cust_a_billtocountrycode;

        @JsonProperty("syy_cust_a_workflowerrors")
        private ValueDetails syy_cust_a_workflowerrors;

        @JsonProperty("scdlab_cust_a_sbilltokey")
        private ValueDetails scdlab_cust_a_sbilltokey;

        @JsonProperty("scdlab_cust_a_sbilltotelephonenumber")
        private ValueDetails scdlab_cust_a_sbilltotelephonenumber;

        @JsonProperty("scdlab_cust_a_sbcompletenessstatus")
        private ValueDetails scdlab_cust_a_sbcompletenessstatus;

        @JsonProperty("scdlab_cust_a_customertype")
        private ValueDetails scdlab_cust_a_customertype;

        @JsonProperty("scdlab_cust_a_updatedDate")
        private ValueDetails scdlab_cust_a_updatedDate;

        @JsonProperty("scdlab_cust_a_billtostatecode")
        private ValueDetails scdlab_cust_a_billtostatecode;

        @JsonProperty("scdlab_cust_a_sourcesystem")
        private ValueDetails scdlab_cust_a_sourcesystem;

        @JsonProperty("scdlab_cust_a_sbilltoopcoid")
        private ValueDetails scdlab_cust_a_sbilltoopcoid;

        @JsonProperty("scdlab_cust_a_gr_creationdate")
        private ValueDetails scdlab_cust_a_gr_creationdate;

        @JsonProperty("scdlab_cust_a_billtoaddressline1")
        private ValueDetails scdlab_cust_a_billtoaddressline1;

    }

}
