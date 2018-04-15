package dao.assessments;

import models.assessments.QuestionHasSalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionHasSalRepository extends JpaRepository<QuestionHasSalEntity, Long> {
}
