package repositories.assessments;

import models.assessments.AssessmentSalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentSalRepository extends JpaRepository<AssessmentSalEntity, Long> {

}
