package repositories.assessments;

import models.assessments.SalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SalRepository extends JpaRepository <SalEntity, Serializable> {


}
