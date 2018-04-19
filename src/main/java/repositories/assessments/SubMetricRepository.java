package repositories.assessments;

import models.assessments.SubMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubMetricRepository extends JpaRepository<SubMetricEntity, Long>{

}
