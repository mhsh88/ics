package com.payAm.core.dto;

import com.payAm.core.criteria.Operator;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/05 - 08:48 AM
 */
public class FilterDto {

    private String field;
    private String value;
    private Operator operator = Operator.EQ;

    public String getField() {
        return this.field;
    }
    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public Object[] getValues() {
        return this.value.split(",");
    }

    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
