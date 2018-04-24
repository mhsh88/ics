package models.assessments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.MetricConstants;
import dtos.assessments.MetricView;
import dtos.assessments.QuestionHasSalView;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static org.hibernate.FetchMode.JOIN;

@Entity
@Table(name = "metric")
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MetricEntity extends BaseEntity implements MetricConstants {
	private static final long serialVersionUID = 1L;

	@JsonView({MetricView.class, QuestionHasSalView.class})
	@Lob
//	@Basic(fetch = FetchType.LAZY)
	public String text;

	@JsonView({MetricView.class, QuestionHasSalView.class})
	public Integer priority;

	@JsonView({MetricView.class, QuestionHasSalView.class})
	public Float weight;

	@JsonView
	@OneToMany(mappedBy = "metric", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
//	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	public List<QuestionHasSalEntity> questionHasSals;

    public MetricEntity() {
    }

    public MetricEntity(Long id, String text, Integer priority, Float weight) {
        super.id = id;
        this.text = text;
        this.priority = priority;
        this.weight = weight;
    }

    public MetricEntity(Long id, String text, Integer priority, Float weight, Collection<QuestionHasSalEntity> questionHasSals) {
        this(id, text, priority, weight);
        this.questionHasSals = (List<QuestionHasSalEntity>) questionHasSals;
    }

    public MetricEntity(String metric) {
        super();
        this.text = metric;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public List<QuestionHasSalEntity> getQuestionHasSals() {
        return questionHasSals;
    }

    public void setQuestionHasSals(List<QuestionHasSalEntity> questionHasSals) {
        this.questionHasSals = questionHasSals;
    }
}