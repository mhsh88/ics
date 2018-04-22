package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.OrganizationAssessmentHasQuestionDao;
import daos.assessments.PreQuestionAnswerDao;
import models.assessments.OrganizationAssessmentHasQuestionEntity;
import repositories.assessments.PreQuestionAnswerRepository;
import dtos.assessments.PreQuestionAnswerView;
import models.assessments.PreQuestionAnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/u/prequestionanswers")
public class PreQuestionAnswerController extends BaseController<PreQuestionAnswerEntity, Long, PreQuestionAnswerView> {
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


    @Inject
    private PreQuestionAnswerDao dao;

    @Override
    public BaseDao<PreQuestionAnswerEntity,Long> getDao() {
        return dao;
    }
}
