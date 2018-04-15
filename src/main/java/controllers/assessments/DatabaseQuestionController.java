package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import dao.assessments.DatabaseQuestionRepository;
import models.assessments.DatabaseQuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/u/databasequestions")
public class DatabaseQuestionController extends BaseController<DatabaseQuestionEntity, Long>{
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
}
