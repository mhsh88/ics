package dao.assessments;

import models.assessments.AssessmentSalEntity;
import models.assessments.SalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AssessmentSalRepository extends JpaRepository<AssessmentSalEntity, Long> {

}
