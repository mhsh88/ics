package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import daos.assessments.AssessmentSalDao;
import daos.assessments.DatabaseQuestionDao;
import repositories.assessments.AssessmentSalRepository;
import dtos.assessments.AssessmentSalView;
import models.assessments.AssessmentSalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:03
 */
@RestController
@RequestMapping("/u/assessmentsals")
public class AssessmentSalController extends BaseController<AssessmentSalEntity, Long, AssessmentSalView> {
//    GET     /assessmentsals/:id                           controllers.assessments.AssessmentSalController.load(id: Long)
//    GET     /assessmentsals                               controllers.assessments.AssessmentSalController.loadModels()
//  TODO  PUT     /assessmentsals                               controllers.assessments.AssessmentSalController.insert()
//     POST    /assessmentsals                               controllers.assessments.AssessmentSalController.update()
//    DELETE  /assessmentsals/:id                           controllers.assessments.AssessmentSalController.delete(id: Long)


    @Autowired
    public AssessmentSalController(AssessmentSalRepository repo) {
            super((CrudRepository) repo);
        }

    @Inject
    private AssessmentSalDao dao;

    @Override
    public BaseDao<AssessmentSalEntity, Long> getDao() {
        return dao;
    }

//    @Autowired
//    AssessmentSalService entityDAO;
//
//
//    @GetMapping(value="/assessmentsals/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AssessmentSalEntity> getEmployeeById(@PathVariable(value="id") Long Id){
//        AssessmentSalEntity emp = (AssessmentSalEntity) entityDAO.byId(Id);
//        if(emp == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok().body(emp);
//    }
//
//    @GetMapping(value="/assessmentsals", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<AssessmentSalEntity> get(){
//        return entityDAO.getAll();
//    }
//
//    @PostMapping("/assessmentsals")
//    public AssessmentSalEntity createEmployee(@Valid @RequestBody AssessmentSalEntity ase){
//        return (AssessmentSalEntity) entityDAO.insert(ase);
//    }
//
//    @DeleteMapping("/assessmentsals/{id}")
//    public ResponseEntity<AssessmentSalEntity> deleteEmployee(@PathVariable(value = "id") Long empId){
//        AssessmentSalEntity emp = (AssessmentSalEntity) entityDAO.byId(empId);
//        if(emp == null ){
//            return ResponseEntity.notFound().build();
//        }
//
//        entityDAO.delete(emp);
//
//        return ResponseEntity.ok().build();
//    }






//
//    @Inject
//    private AssessmentSalDao repositories;
//
//    @Override
//    public BaseDao<Long, AssessmentSalEntity> getDao() {
//        return repositories;
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