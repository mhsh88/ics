package repositories.assessments;

import models.assessments.PreQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreQuestionRepository extends JpaRepository<PreQuestionEntity, Long>{
}
