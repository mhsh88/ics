package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import dao.assessments.SubMetricRepository;
import dtos.assessments.SubMetricView;
import models.assessments.SubMetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u/submetrics")
public class SubMetricController extends BaseController<SubMetricEntity, Long, SubMetricView>{

//### subMetric ###
//    GET     /submetrics/:id                               controllers.assessments.SubMetricController.load(id: Long)
//    GET     /submetrics                                   controllers.assessments.SubMetricController.loadModels()
//    PUT     /submetrics                                   controllers.assessments.SubMetricController.insert()
//    POST    /submetrics                                   controllers.assessments.SubMetricController.update()
//    DELETE  /submetrics/:id                               controllers.assessments.SubMetricController.delete(id: Long)

    @Autowired
    public SubMetricController(SubMetricRepository repo){
        super((CrudRepository) repo);
    }
}

