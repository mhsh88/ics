package dao.assessments;

import models.assessments.PreQuestionAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreQuestionAnswerRepository extends JpaRepository<PreQuestionAnswerEntity, Long>{
}
