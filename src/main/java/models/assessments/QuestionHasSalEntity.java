package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.QuestionHasSalConstants;
import dtos.assessments.QuestionHasSalView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="question_has_sal")
@EntityListeners({AuditingEntityListener.class})
public class QuestionHasSalEntity extends BaseEntity implements QuestionHasSalConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(QuestionHasSalView.class)
	@ManyToOne
	@JoinColumn(name = "sal_id")
	@Basic(fetch = FetchType.LAZY)
	public SalEntity sal;

	@JsonView(QuestionHasSalView.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "metric_id")
	public MetricEntity metric;

	@JsonView(QuestionHasSalView.class)
	@ManyToOne
	@JoinColumn(name = "sub_metric_id")
	@Basic(fetch = FetchType.LAZY)
	public SubMetricEntity subMetric;

	@JsonView(QuestionHasSalView.class)
	@ManyToOne
	@JoinColumn(name = "question_id")
	@Basic(fetch = FetchType.LAZY)
	public QuestionEntity question;

	@JsonView(QuestionHasSalView.class)
	@ManyToOne
	@JoinColumn(name = "pre_question_id")
	@Basic(fetch = FetchType.LAZY)
	public PreQuestionEntity preQuestion;

	@JsonView(QuestionHasSalView.class)
	@ManyToOne
	@JoinColumn(name = "standard_id")
	@Basic(fetch = FetchType.LAZY)
	public StandardEntity standard;

	@JsonView(QuestionHasSalView.class)
	public Integer priority;

	@JsonView(QuestionHasSalView.class)
	public Float weight;
}