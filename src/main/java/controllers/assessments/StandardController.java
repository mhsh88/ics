package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import dao.assessments.StandardRepository;
import models.assessments.StandardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u/standards")
public class StandardController  extends BaseController<StandardEntity, Long>{
//    ### standard ###
//    GET     /standards/:id                                controllers.assessments.StandardController.load(id: Long)
//    GET     /standards                                    controllers.assessments.StandardController.loadModels()
//    PUT     /standards                                    controllers.assessments.StandardController.insert()
//    POST    /standards                                    controllers.assessments.StandardController.update()
//    DELETE  /standards/:id                                controllers.assessments.StandardController.delete(id: Long)


    @Autowired
    public StandardController(StandardRepository repo){
        super((CrudRepository) repo);
    }
}
