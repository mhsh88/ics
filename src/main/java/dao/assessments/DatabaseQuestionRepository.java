package dao.assessments;

import models.assessments.DatabaseQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseQuestionRepository extends JpaRepository<DatabaseQuestionEntity, Long> {
}
