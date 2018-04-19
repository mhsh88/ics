package service.assessments;

import repositories.assessments.AssessmentSalRepository;
import models.assessments.AssessmentSalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public abstract class AssessmentSalService<I> {

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
    public abstract void delete(I id);
//    {
//        repository.delete((Serializable) id);
//    }
    public List<AssessmentSalEntity> getAll(){
        return (List<AssessmentSalEntity>) repository.findAll();
    }
    public abstract AssessmentSalEntity byId(I id);
//    {
//        return (AssessmentSalEntity) repository.findOne((Serializable) id);
//    }
}

