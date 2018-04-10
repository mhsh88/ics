package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.PreQuestionAnswerConstants;
import dtos.assessments.PreQuestionAnswerView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="pre_quesion_answer")
@EntityListeners({AuditingEntityListener.class})
public class PreQuestionAnswerEntity extends BaseEntity implements PreQuestionAnswerConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(PreQuestionAnswerView.class)
	@Column(name="answer_comment")
	@Lob
	public String answerComment;

	@JsonView(PreQuestionAnswerView.class)
	@Column(name="answer_value")
	@Size(max = 45)
	public String answerValue;

	@JsonView(PreQuestionAnswerView.class)
	@ManyToOne
	@JoinColumn(name = "pre_question_id")
	public PreQuestionEntity preQuestion;
}