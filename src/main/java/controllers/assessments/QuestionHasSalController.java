package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.QuestionAnswerDao;
import daos.assessments.QuestionHasSalDao;
import models.assessments.*;
import repositories.assessments.QuestionHasSalRepository;
import dtos.assessments.QuestionHasSalView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static com.payAm.core.util.StringUtil.DOT;

@RestController
@RequestMapping("/u/questionhassals")
public class QuestionHasSalController extends BaseController<QuestionHasSalEntity, Long, QuestionHasSalView> {

//    ### questionHasSal ###
//    GET     /questionhassals/:id                          controllers.assessments.QuestionHasSalController.load(id: Long)
//    GET     /questionhassals                              controllers.assessments.QuestionHasSalController.loadModels()
//    PUT     /questionhassals                              controllers.assessments.QuestionHasSalController.insert()
//    POST    /questionhassals                              controllers.assessments.QuestionHasSalController.update()
//    DELETE  /questionhassals/:id                          controllers.assessments.QuestionHasSalController.delete(id: Long)

    @Autowired
    public QuestionHasSalController(QuestionHasSalRepository repo){
        super((CrudRepository) repo);
    }


    @Inject
    private QuestionHasSalDao dao;

    @Override
    public BaseDao<QuestionHasSalEntity,Long> getDao() {
        return dao;
    }

    @Override
    protected List<String> getFetchedFields() {
        List<String> fields = super.getFetchedFields();
        fields.add(SalEntity.ENTITY + DOT + SalEntity.VALUE);
        fields.add(StandardEntity.ENTITY + DOT + StandardEntity.TEXT);
        fields.add(MetricEntity.ENTITY + DOT + MetricEntity.TEXT);
        fields.add(SubMetricEntity.ENTITY + DOT + SubMetricEntity.TEXT);
        fields.add(QuestionEntity.ENTITY + DOT + QuestionEntity.TEXT);
        fields.add(PreQuestionEntity.ENTITY + DOT + PreQuestionEntity.TEXT);
        return fields;
    }
}
