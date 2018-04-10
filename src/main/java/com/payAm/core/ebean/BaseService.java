package com.payAm.core.ebean;

import com.payAm.core.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class BaseService<I extends Serializable, E extends BaseEntity> {

    @Autowired
    BaseDAORepository repository;

    public E insert(E e){
        return (E) repository.save(e);
    }
    public E update(E e){
        return (E) repository.save(e);
    }
    public void delete(E e){
        repository.delete(e);
    }
    public void delete(I id){
        repository.delete(id);
    }
    public List<E> getAll(){
        return (List<E>) repository.findAll();
    }
    public E byId(I id){
        return (E) repository.findOne(id);
    }
}
