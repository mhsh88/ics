package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import dao.assessments.OrganizationAssessmentHasQuestionRepository;
import models.assessments.OrganizationAssessmentHasQuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u/organizationassessmenthasquestions")
public class OrganizationAssessmentHasQuestionController extends BaseController<OrganizationAssessmentHasQuestionEntity, Long> {
//
//### organizationAssessmentHasQuestion ###
//    GET     /organizationassessmenthasquestions/:id       controllers.assessments.OrganizationAssessmentHasQuestionController.load(id: Long)
//    GET     /organizationassessmenthasquestions           controllers.assessments.OrganizationAssessmentHasQuestionController.loadModels()
//    PUT     /organizationassessmenthasquestions           controllers.assessments.OrganizationAssessmentHasQuestionController.insert()
//    POST    /organizationassessmenthasquestions           controllers.assessments.OrganizationAssessmentHasQuestionController.update()
//    DELETE  /organizationassessmenthasquestions/:id       controllers.assessments.OrganizationAssessmentHasQuestionController.delete(id: Long)
    @Autowired
    public OrganizationAssessmentHasQuestionController (OrganizationAssessmentHasQuestionRepository repo){
        super((CrudRepository) repo);
    }
}
