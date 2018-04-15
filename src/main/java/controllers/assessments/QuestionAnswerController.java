package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import constants.assessments.QuestionConstants;
import dao.assessments.PreQuestionAnswerRepository;
import dao.assessments.QuestionAnswerRepository;
import models.assessments.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u/questionanswers")
public class QuestionAnswerController extends BaseController<QuestionEntity, Long>  {

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
}
