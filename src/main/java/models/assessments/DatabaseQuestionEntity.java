package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.DatabaseQuestionConstants;
import dtos.assessments.DatabaseQuestionView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="database_question")
@EntityListeners({AuditingEntityListener.class})
public class DatabaseQuestionEntity extends BaseEntity implements DatabaseQuestionConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(DatabaseQuestionView.class)
	@Size(max = 45)
	public String metric;

	@JsonView(DatabaseQuestionView.class)
	@Column(name="metric_priority")
	public Integer metricPriority;

	@JsonView(DatabaseQuestionView.class)
	@Column(name="metric_weight")
	public Float metricWeight;

	@JsonView(DatabaseQuestionView.class)
	@Column(name="question_text")
	@Size(max = 1000)
	public String questionText;

	@JsonView(DatabaseQuestionView.class)
	@Size(max = 45)
	public String standard;

	@JsonView(DatabaseQuestionView.class)
	@Column(name="sub_metric")
	@Size(max = 45)
	public String subMetric;

	@JsonView(DatabaseQuestionView.class)
	@Column(name="sub_metric_priority")
	public Integer subMetricPriority;

	@JsonView(DatabaseQuestionView.class)
	@Column(name="sub_metric_weight")
	public Float subMetricWeight;
}