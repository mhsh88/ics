package com.payAm.core.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.constant.BaseConstants;
import com.payAm.core.view.BaseView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


//@Component

//@EntityListeners({AuditingEntityListener.class})

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity <I extends Serializable> implements BaseConstants, BaseModel  {

    @JsonView(BaseView.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long id;

    @JsonView(BaseView.class)
    public Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

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
