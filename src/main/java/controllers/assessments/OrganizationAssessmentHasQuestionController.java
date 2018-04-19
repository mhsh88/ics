package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.DatabaseQuestionDao;
import daos.assessments.OrganizationAssessmentHasQuestionDao;
import models.assessments.DatabaseQuestionEntity;
import repositories.assessments.OrganizationAssessmentHasQuestionRepository;
import dtos.assessments.OrganizationAssessmentView;
import models.assessments.OrganizationAssessmentHasQuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/u/organizationassessmenthasquestions")
public class OrganizationAssessmentHasQuestionController extends BaseController<OrganizationAssessmentHasQuestionEntity, Long, OrganizationAssessmentView> {
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


    @Inject
    private OrganizationAssessmentHasQuestionDao dao;

    @Override
    public BaseDao<Long, OrganizationAssessmentHasQuestionEntity> getDao() {
        return dao;
    }
}
