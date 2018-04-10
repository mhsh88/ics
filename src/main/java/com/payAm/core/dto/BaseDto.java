package com.payAm.core.dto;

import com.payAm.core.constant.BaseConstants;

import java.io.Serializable;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/05 - 06:33 AM
 */
public abstract class BaseDto<I extends Serializable> implements BaseConstants {

    protected I id;
    protected Boolean deleted;

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public abstract String getTitlePropertyValue(String lang);

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (this.getClass().equals(object.getClass())) {
            if (getId() != null) {
                return getId().equals(((BaseDto<?>) object).getId());
            } else {
                return super.equals(object);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
