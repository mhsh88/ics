package dao.assessments;

import models.assessments.StandardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<StandardEntity, Long> {
}
