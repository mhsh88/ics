package repositories.assessments;

import models.assessments.OrganizationAssessmentHasQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationAssessmentHasQuestionRepository extends JpaRepository<OrganizationAssessmentHasQuestionEntity, Long> {
}
