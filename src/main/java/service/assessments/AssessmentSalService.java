package service.assessments;

import com.payAm.core.ebean.BaseService;
import com.payAm.core.model.BaseEntity;
import dao.assessments.AssessmentSalRepository;
import models.assessments.AssessmentSalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
public class AssessmentSalService<I> {

    @Autowired
    AssessmentSalRepository repository;

    public AssessmentSalEntity insert(AssessmentSalEntity e){
        return repository.save(e);
    }
    public AssessmentSalEntity update(AssessmentSalEntity e){
        return  repository.save(e);
    }
    public void delete(AssessmentSalEntity e){
        repository.delete(e);
    }
    public void delete(I id){
        repository.delete((Serializable) id);
    }
    public List<AssessmentSalEntity> getAll(){
        return (List<AssessmentSalEntity>) repository.findAll();
    }
    public AssessmentSalEntity byId(I id){
        return (AssessmentSalEntity) repository.findOne((Serializable) id);
    }
}

