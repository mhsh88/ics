package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.OrganizationAssessmentHasQuestionConstants;
import dtos.assessments.OrganizationAssessmentHasQuestionView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="organization_assessment_has_question")
@EntityListeners({AuditingEntityListener.class})
public class OrganizationAssessmentHasQuestionEntity extends BaseEntity implements OrganizationAssessmentHasQuestionConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(OrganizationAssessmentHasQuestionView.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_answer_id")
	public QuestionAnswerEntity questionAnswer;

	@JsonView(OrganizationAssessmentHasQuestionView.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pre_question_answer_id")
	public PreQuestionAnswerEntity preQuestionAnswer;

	@JsonView(OrganizationAssessmentHasQuestionView.class)
	@ManyToOne
	@JoinColumn(name = "organization_assessment_id")
	public OrganizationAssessmentEntity organizationAssessment;

	@JsonView(OrganizationAssessmentHasQuestionView.class)
	@Column(name="mark_as_view")
	public Boolean markAsView;

	@JsonView(OrganizationAssessmentHasQuestionView.class)
	public Integer priority;

	@JsonView(OrganizationAssessmentHasQuestionView.class)
	public Float weight;
}