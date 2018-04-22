package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.AssessmentSalDao;
import daos.assessments.DatabaseQuestionDao;
import models.assessments.AssessmentSalEntity;
import repositories.assessments.DatabaseQuestionRepository;
import dtos.assessments.DatabaseQuestionView;
import models.assessments.DatabaseQuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


@RestController
@RequestMapping("/u/databasequestions")
public class DatabaseQuestionController extends BaseController<DatabaseQuestionEntity, Long, DatabaseQuestionView>{
//    ### databaseQuestion ###
//    GET     /databasequestions/:id                        controllers.assessments.DatabaseQuestionController.load(id: Long)
//    GET     /databasequestions                            controllers.assessments.DatabaseQuestionController.loadModels()
//    PUT     /databasequestions                            controllers.assessments.DatabaseQuestionController.insert()
//    POST    /databasequestions                            controllers.assessments.DatabaseQuestionController.update()
//    DELETE  /databasequestions/:id                        controllers.assessments.DatabaseQuestionController.delete(id: Long)
    @Autowired
    public DatabaseQuestionController(DatabaseQuestionRepository repo) {
                super((CrudRepository) repo);
            }

    @Inject
    private DatabaseQuestionDao dao;

    @Override
    public BaseDao<DatabaseQuestionEntity,Long> getDao() {
        return dao;
    }
}
