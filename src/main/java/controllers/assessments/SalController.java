package controllers.assessments;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseService;
import models.assessments.SalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:03
 */
@RestController
@RequestMapping("/u")
public class SalController{
//    ### sal ###
//    GET     /sals/:id                                     controllers.assessments.SalController.load(id: Long)
//    GET     /sals                                         controllers.assessments.SalController.loadModels()
//    PUT   TODO  /sals                                         controllers.assessments.SalController.insert()
//    POST    /sals                                         controllers.assessments.SalController.update()
//    DELETE  /sals/:id                                     controllers.assessments.SalController.delete(id: Long)
    @Autowired
    BaseService entityDAO;


    @GetMapping(value="/sals/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalEntity> getEmployeeById(@PathVariable(value="id") Long Id){
        SalEntity emp = (SalEntity) entityDAO.byId(Id);
        if(emp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(emp);
    }

    @GetMapping(value="/sals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SalEntity> get(){
        return entityDAO.getAll();
    }

    @PostMapping("/sals")
    public SalEntity createEmployee(@Valid @RequestBody SalEntity ase){
        return (SalEntity) entityDAO.insert(ase);
    }

    @DeleteMapping("/sals/{id}")
    public ResponseEntity<SalEntity> deleteEmployee(@PathVariable(value = "id") Long empId){
        SalEntity emp = (SalEntity) entityDAO.byId(empId);
        if(emp == null ){
            return ResponseEntity.notFound().build();
        }

        entityDAO.delete(emp);

        return ResponseEntity.ok().build();
    }
}