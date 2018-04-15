package dao.assessments;

import models.assessments.OrganizationAssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationAssessmentRepository extends JpaRepository<OrganizationAssessmentEntity, Long> {
}
