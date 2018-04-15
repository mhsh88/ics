package dao.assessments;

import com.payAm.core.model.BaseEntity;
import models.assessments.SalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SalRepository extends JpaRepository <SalEntity, Serializable> {


}
