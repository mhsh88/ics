package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import dao.assessments.OrganizationAssessmentRepository;
import dtos.assessments.OrganizationAssessmentView;
import models.assessments.OrganizationAssessmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u/organizationassessments")
public class OrganizationAssessmentController extends BaseController<OrganizationAssessmentEntity, Long, OrganizationAssessmentView> {
//    ### organizationAssessment ###
//    GET     /organizationassessments/:id                  controllers.assessments.OrganizationAssessmentController.load(id: Long)
//    GET     /organizationassessments                      controllers.assessments.OrganizationAssessmentController.loadModels()
//    PUT     /organizationassessments                      controllers.assessments.OrganizationAssessmentController.insert()
//    POST    /organizationassessments                      controllers.assessments.OrganizationAssessmentController.update()
//    DELETE  /organizationassessments/:id                  controllers.assessments.OrganizationAssessmentController.delete(id: Long)

    @Autowired
    public OrganizationAssessmentController(OrganizationAssessmentRepository repo){
        super((CrudRepository) repo);
    }
}
