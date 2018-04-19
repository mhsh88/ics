package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.QuestionAnswerDao;
import models.assessments.QuestionAnswerEntity;
import repositories.assessments.QuestionAnswerRepository;
import dtos.assessments.QuestionView;
import models.assessments.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/u/questionanswers")
public class QuestionAnswerController extends BaseController<QuestionAnswerEntity, Long, QuestionView>  {

//    ### questionAnswer ###
//    GET     /questionanswers/:id                          controllers.assessments.QuestionAnswerController.load(id: Long)
//    GET     /questionanswers                              controllers.assessments.QuestionAnswerController.loadModels()
//    PUT     /questionanswers                              controllers.assessments.QuestionAnswerController.insert()
//    POST    /questionanswers                              controllers.assessments.QuestionAnswerController.update()
//    DELETE  /questionanswers/:id                          controllers.assessments.QuestionAnswerController.delete(id: Long)

    @Autowired
    public QuestionAnswerController(QuestionAnswerRepository repo){
        super((CrudRepository) repo);
    }


    @Inject
    private QuestionAnswerDao dao;

    @Override
    public BaseDao<Long, QuestionAnswerEntity> getDao() {
        return dao;
    }
}
