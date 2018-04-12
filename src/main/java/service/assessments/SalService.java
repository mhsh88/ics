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
public class SalService<SalEntity> extends BaseService {

    @Autowired
    SalRepository repository;

    public SalService() {
        setRepository(this.repository);
    }

//    @Override
//    public BaseEntity update(BaseEntity baseEntity) {
//        return null;
//    }
//
//    @Override
//    public void delete(BaseEntity baseEntity) {
//
//    }
//
//    @Override
//    public void delete(Serializable id) {
//
//    }
//
//    @Override
//    public List<SalEntity> getAll() {
//        return (List<SalEntity>) repository.findAll();
//    }
//
//    @Override
//    public BaseEntity byId(Serializable id) {
//        return null;
//    }
}
