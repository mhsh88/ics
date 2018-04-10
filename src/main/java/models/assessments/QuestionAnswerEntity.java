package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.QuestionAnswerConstants;
import dtos.assessments.QuestionAnswerView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="question_answer")
@EntityListeners({AuditingEntityListener.class})
public class QuestionAnswerEntity extends BaseEntity implements QuestionAnswerConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(QuestionAnswerView.class)
	@Column(name="answer_value")
	@Size(max = 45)
	public String answerValue;

	@JsonView(QuestionAnswerView.class)
	@Size(max = 1000)
	public String comment;

	@JsonView(QuestionAnswerView.class)
	@ManyToOne
	@JoinColumn(name = "question_id")
	public QuestionEntity question;
}