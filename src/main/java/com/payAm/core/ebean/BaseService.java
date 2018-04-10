package com.payAm.core.ebean;

import com.payAm.core.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.List;

@MappedSuperclass
@Transactional
public abstract class BaseService<I extends Serializable, E extends BaseEntity> {



    @Autowired
    BaseDAORepository repository;

    public BaseService() {
        setRepository(getRepository());
    }

    public JpaRepository getRepository() {
        return this.repository;
    }

    public void setRepository(JpaRepository repository) {
        this.repository = (BaseDAORepository) repository;
    }

    public E insert(E e){
        return (E) getRepository().save(e);
    }
    public E update(E e){
        return (E) getRepository().save(e);
    }
    public void delete(E e){
        getRepository().delete(e);
    }
    public void delete(I id){
        getRepository().delete(id);
    }
    public List<E> getAll(){
        return (List<E>) getRepository().findAll();
    }
    public E byId(I id){
        return (E) getRepository().findOne(id);
    }
}
