package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.DatabaseQuestionDao;
import daos.assessments.MetricDao;
import models.assessments.DatabaseQuestionEntity;
import repositories.assessments.MetricRepository;
import dtos.assessments.MetricView;
import models.assessments.MetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/u/metrics")
public class MetricController extends BaseController<MetricEntity, Long, MetricView> {

//### metric ###
//    GET     /metrics/:id                                  controllers.assessments.MetricController.load(id: Long)
//    GET     /metrics                                      controllers.assessments.MetricController.loadModels()
//    PUT     /metrics                                      controllers.assessments.MetricController.insert()
//    POST    /metrics                                      controllers.assessments.MetricController.update()
//    DELETE  /metrics/:id                                  controllers.assessments.MetricController.delete(id: Long)

    @Autowired
    public MetricController (MetricRepository repo){
        super( (CrudRepository) repo);
    }


    @Inject
    private MetricDao dao;

    @Override
    public BaseDao<MetricEntity,Long> getDao() {
        return dao;
    }


}
