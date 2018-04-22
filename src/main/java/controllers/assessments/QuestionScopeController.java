package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.QuestionAnswerDao;
import daos.assessments.QuestionScopeDao;
import models.assessments.*;
import repositories.assessments.QuestionScopeRepository;
import dtos.assessments.QuestionScopeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.payAm.core.util.StringUtil.DOT;

@RestController
@RequestMapping("/u/questionscopes")
public class QuestionScopeController  extends BaseController<QuestionScopeEntity, Long, QuestionScopeView>{
//    ### questionScope ###
//    GET     /questionscopes/:id                           controllers.assessments.QuestionScopeController.load(id: Long)
//    GET     /questionscopes                               controllers.assessments.QuestionScopeController.loadModels()
//    PUT     /questionscopes                               controllers.assessments.QuestionScopeController.insert()
//    POST    /questionscopes                               controllers.assessments.QuestionScopeController.update()
//    DELETE  /questionscopes/:id                           controllers.assessments.QuestionScopeController.delete(id: Long)

    @Autowired
    public QuestionScopeController(QuestionScopeRepository repo){
        super((CrudRepository) repo);
    }


    @Inject
    private QuestionScopeDao dao;

    @Override
    public BaseDao<Long, QuestionScopeEntity> getDao() {
        return dao;
    }

    @Override
    protected List<String> getFetchedFields() {
        return super.getFetchedFields();
//        List<String> fields = new ArrayList<>();
//        fields.add(QuestionScopeEntity.ID);
////        fields.add(QuestionScopeEntity.DELETED);
//        fields.add(QuestionScopeEntity.VALUE);
//
//        return fields;
    }
}
