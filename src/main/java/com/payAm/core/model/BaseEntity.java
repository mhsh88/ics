package com.payAm.core.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.constant.BaseConstants;
import com.payAm.core.view.BaseView;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity <I extends Serializable> implements BaseConstants  {

    @JsonView(BaseView.class)
    @Id
    public Long id;

    @JsonView(BaseView.class)
    public Boolean deleted = false;

//    public abstract E insert();
//    public abstract E update();
//    public abstract E byId(I id);

    public String getTitlePropertyValue(String lang) {
        // TODO: make this function abstract
        return "";
    }


    public void logicallyDelete() {
        this.deleted = true;
    }

    public void logicallyRetrieve() {
        this.deleted = false;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (this.getClass().equals(object.getClass())) {
            if (id != null) {
                return id.equals(((BaseEntity) object).id);
            } else {
                return super.equals(object);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

}
