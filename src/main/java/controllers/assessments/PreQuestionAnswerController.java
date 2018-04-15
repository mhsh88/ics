package controllers.assessments;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.payAm.core.ebean.BaseController;
import dao.assessments.PreQuestionAnswerRepository;
import models.assessments.PreQuestionAnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u/prequestionanswers")
public class PreQuestionAnswerController extends BaseController<PreQuestionAnswerEntity, Long> {
//    ### preQuestionAnswer ###
//    GET     /prequestionanswers/:id                       controllers.assessments.PreQuestionAnswerController.load(id: Long)
//    GET     /prequestionanswers                           controllers.assessments.PreQuestionAnswerController.loadModels()
//    PUT     /prequestionanswers                           controllers.assessments.PreQuestionAnswerController.insert()
//    POST    /prequestionanswers                           controllers.assessments.PreQuestionAnswerController.update()
//    DELETE  /prequestionanswers/:id                       controllers.assessments.PreQuestionAnswerController.delete(id: Long)
    @Autowired
    public PreQuestionAnswerController(PreQuestionAnswerRepository repo){
        super((CrudRepository) repo);
    }
}
