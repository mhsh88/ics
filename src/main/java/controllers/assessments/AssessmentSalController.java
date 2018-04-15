package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseService;
import dao.assessments.AssessmentSalRepository;
import models.assessments.AssessmentSalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.assessments.AssessmentSalService;

import javax.validation.Valid;
import java.util.List;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:03
 */
@RestController
@RequestMapping("/u")
public class AssessmentSalController extends BaseController {
//    GET     /assessmentsals/:id                           controllers.assessments.AssessmentSalController.load(id: Long)
//    GET     /assessmentsals                               controllers.assessments.AssessmentSalController.loadModels()
//  TODO  PUT     /assessmentsals                               controllers.assessments.AssessmentSalController.insert()
//     POST    /assessmentsals                               controllers.assessments.AssessmentSalController.update()
//    DELETE  /assessmentsals/:id                           controllers.assessments.AssessmentSalController.delete(id: Long)

    @Autowired
    AssessmentSalService entityDAO;


    @GetMapping(value="/assessmentsals/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssessmentSalEntity> getEmployeeById(@PathVariable(value="id") Long Id){
        AssessmentSalEntity emp = (AssessmentSalEntity) entityDAO.byId(Id);
        if(emp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(emp);
    }

    @GetMapping(value="/assessmentsals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssessmentSalEntity> get(){
        return entityDAO.getAll();
    }

    @PostMapping("/assessmentsals")
    public AssessmentSalEntity createEmployee(@Valid @RequestBody AssessmentSalEntity ase){
        return (AssessmentSalEntity) entityDAO.insert(ase);
    }

    @DeleteMapping("/assessmentsals/{id}")
    public ResponseEntity<AssessmentSalEntity> deleteEmployee(@PathVariable(value = "id") Long empId){
        AssessmentSalEntity emp = (AssessmentSalEntity) entityDAO.byId(empId);
        if(emp == null ){
            return ResponseEntity.notFound().build();
        }

        entityDAO.delete(emp);

        return ResponseEntity.ok().build();
    }






//
//    @Inject
//    private AssessmentSalDao dao;
//
//    @Override
//    public BaseDao<Long, AssessmentSalEntity> getDao() {
//        return dao;
//    }
//
//    @Override
//    @Transactional
//    //@Pattern(Permission.ASSESSMENT_SAL_UPDATE)
//    public Result insert() {
//        return super.insert();
//    }
//
//    @Override
//    @Transactional
//    //@Pattern(Permission.ASSESSMENT_SAL_UPDATE)
//    public Result update() {
//        return super.update();
//    }
//
//    @Override
//    @Transactional
//    //@Pattern(Permission.ASSESSMENT_SAL_DELETE)
//    public Result delete(Long id) {
//        return super.delete(id);
//    }
}