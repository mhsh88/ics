package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.QuestionAnswerDao;
import daos.assessments.QuestionDao;
import models.assessments.QuestionAnswerEntity;
import repositories.assessments.QuestionRepository;
import dtos.assessments.QuestionView;
import models.assessments.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/u/questions")
public class QuestionController extends BaseController<QuestionEntity, Long, QuestionView> {

//    ### question ###
//    GET     /questions/:id                                controllers.assessments.QuestionController.load(id: Long)
//    GET     /questions                                    controllers.assessments.QuestionController.loadModels()
//    PUT     /questions                                    controllers.assessments.QuestionController.insert()
//    POST    /questions                                    controllers.assessments.QuestionController.update()
//    DELETE  /questions/:id                                controllers.assessments.QuestionController.delete(id: Long)
    @Autowired
    public QuestionController(QuestionRepository repo){
        super((CrudRepository) repo);
    }


    @Inject
    private QuestionDao dao;

    @Override
    public BaseDao<Long, QuestionEntity> getDao() {
        return dao;
    }

}
