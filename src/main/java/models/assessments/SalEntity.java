package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.SalConstants;
import dtos.assessments.AssessmentSalView;
import dtos.assessments.QuestionHasSalView;
import dtos.assessments.SalView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sal")
@EntityListeners({AuditingEntityListener.class})
public class SalEntity extends BaseEntity implements SalConstants {
	private static final long serialVersionUID = 1L;

	@JsonView({SalView.class, AssessmentSalView.class, QuestionHasSalView.class})
	@Size(max = 45)
	private String value;

	@JsonView
	@OneToMany(mappedBy = "sal")
	private List<AssessmentSalEntity> assessmentSals;

	@JsonView
	@OneToMany(mappedBy = "sal")
	private List<QuestionHasSalEntity> questionHasSals;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<AssessmentSalEntity> getAssessmentSals() {
		return assessmentSals;
	}

	public void setAssessmentSals(List<AssessmentSalEntity> assessmentSals) {
		this.assessmentSals = assessmentSals;
	}

	public List<QuestionHasSalEntity> getQuestionHasSals() {
		return questionHasSals;
	}

	public void setQuestionHasSals(List<QuestionHasSalEntity> questionHasSals) {
		this.questionHasSals = questionHasSals;
	}
}