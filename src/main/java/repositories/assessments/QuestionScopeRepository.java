package repositories.assessments;

import models.assessments.QuestionScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionScopeRepository extends JpaRepository<QuestionScopeEntity, Long> {
}
