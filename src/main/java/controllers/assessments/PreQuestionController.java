package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.PreQuestionAnswerDao;
import daos.assessments.PreQuestionDao;
import models.assessments.PreQuestionAnswerEntity;
import repositories.assessments.PreQuestionRepository;
import dtos.assessments.PreQuestionView;
import models.assessments.PreQuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/u/prequestions")
public class PreQuestionController extends BaseController<PreQuestionEntity, Long, PreQuestionView> {
//    ### preQuestion ###
//    GET     /prequestions/:id                             controllers.assessments.PreQuestionController.load(id: Long)
//    GET     /prequestions                                 controllers.assessments.PreQuestionController.loadModels()
//    PUT     /prequestions                                 controllers.assessments.PreQuestionController.insert()
//    POST    /prequestions                                 controllers.assessments.PreQuestionController.update()
//    DELETE  /prequestions/:id                             controllers.assessments.PreQuestionController.delete(id: Long)

    @Autowired
    public PreQuestionController(PreQuestionRepository repo){
        super((CrudRepository) repo);
    }


    @Inject
    private PreQuestionDao dao;

    @Override
    public BaseDao<Long, PreQuestionEntity> getDao() {
        return dao;
    }
}
