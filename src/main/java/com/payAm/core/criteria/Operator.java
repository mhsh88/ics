package com.payAm.core.criteria;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payAm.core.i18n.CoreMessagesCodes;

import java.util.HashMap;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/02 - 07:39 PM
 */
public enum Operator {

    @JsonProperty("eq")
    EQ(1, CoreMessagesCodes.EQ),
    @JsonProperty("ne")
    NE(2, CoreMessagesCodes.NE),
    @JsonProperty("gt")
    GT(3, CoreMessagesCodes.GT),
    @JsonProperty("ge")
    GE(4, CoreMessagesCodes.GE),
    @JsonProperty("lt")
    LT(5, CoreMessagesCodes.LT),
    @JsonProperty("le")
    LE(6, CoreMessagesCodes.LE),
    @JsonProperty("isNull")
    IS_NULL(7, CoreMessagesCodes.IS_NULL),
    @JsonProperty("isNotNull")
    IS_NOT_NULL(8, CoreMessagesCodes.IS_NOT_NULL),
    @JsonProperty("like")
    LIKE(9, CoreMessagesCodes.LIKE),
    @JsonProperty("startsWith")
    STARTS_WITH(10, CoreMessagesCodes.STARTS_WITH),
    @JsonProperty("endsWith")
    ENDS_WITH(11, CoreMessagesCodes.ENDS_WITH),
    @JsonProperty("in")
    IN(12, CoreMessagesCodes.IN),
    @JsonProperty("jsonEq")
    JSON_EQ(13, CoreMessagesCodes.JSON_EQ),
    @JsonProperty("jsonExists")
    JSON_EXISTS(14, CoreMessagesCodes.JSON_EXISTS),
    @JsonProperty("jsonNe")
    JSON_NE(15, CoreMessagesCodes.JSON_NE),
    @JsonProperty("jsonNotExists")
    JSON_NOT_EXISTS(16, CoreMessagesCodes.JSON_NOT_EXISTS),
    @JsonProperty("and")
    AND(17, CoreMessagesCodes.AND),
    @JsonProperty("or")
    OR(18, CoreMessagesCodes.OR),
    @JsonProperty("not")
    NOT(19, CoreMessagesCodes.NOT);

    private static HashMap<Integer, String> options;

    private Integer code;
    private String titleCode;

    Operator(Integer code, String titleCode) {
        this.code = code;
        this.titleCode = titleCode;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeTitle() {
        return titleCode;
    }

    public static HashMap<Integer, String> getOptions() {
        if (options == null) {
            options = new HashMap<>();
            for (Operator operator : values()) {
                options.put(operator.getCode(), operator.getCodeTitle());
            }
        }
        return options;
    }
}
