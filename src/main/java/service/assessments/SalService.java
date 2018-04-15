package service.assessments;


import com.payAm.core.ebean.BaseService;
import com.payAm.core.model.BaseEntity;
import dao.assessments.SalRepository;
import models.assessments.SalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SalService<I> {

    @Autowired
    SalRepository repository;

    public SalEntity insert(SalEntity e){
        return repository.save(e);
    }
    public SalEntity update(SalEntity e){
        return  repository.save(e);
    }
    public void delete(SalEntity e){
        repository.delete(e);
    }
    public void delete(I id){
        repository.delete((Serializable) id);
    }
    public List<SalEntity> getAll(){
        return (List<SalEntity>) repository.findAll();
    }
    public SalEntity byId(I id){
        return (SalEntity) repository.findOne((Serializable) id);
    }
}
