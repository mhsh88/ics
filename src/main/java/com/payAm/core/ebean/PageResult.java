package com.payAm.core.ebean;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.view.BaseView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/05 - 09:14 AM
 */
public class PageResult<M> implements Serializable {

    @JsonView(BaseView.class)
    private Boolean success = true;

    @JsonView(BaseView.class)
    private List<M> data;

    @JsonView(BaseView.class)
    private int total = 0;

    @JsonView(BaseView.class)
    private String message;

    public Boolean getSuccess() {
        return success;
    }
    public void unsuccessfulOperation(String errorMessage) {
        this.success = false;
        this.message = errorMessage;
    }

    public List<M> getData() {
        if (data == null)
            data = new ArrayList<>();
        return data;
    }
    public void setData(List<M> data) {
        this.data = data;
    }
    public void addData(M model) {
        getData().add(model);
    }

    public int getTotal() {
        if (total == 0)
            return getData().size();
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
