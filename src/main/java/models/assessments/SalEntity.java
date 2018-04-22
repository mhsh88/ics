package models.assessments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.SalConstants;
//import dtos.assessments.AssessmentSalView;
//import dtos.assessments.QuestionHasSalView;
//import dtos.assessments.SalView;
import dtos.assessments.AssessmentSalView;
import dtos.assessments.QuestionHasSalView;
import dtos.assessments.SalView;
import org.hibernate.mapping.FetchProfile;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sal")
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SalEntity extends BaseEntity implements SalConstants {
	private static final long serialVersionUID = 1L;

	@JsonView({SalView.class, AssessmentSalView.class, QuestionHasSalView.class})
	@Size(max = 45)
//	@Basic(fetch = FetchType.LAZY)
	private String value;

	@JsonView
	@OneToMany(mappedBy = "sal")
//	@Basic(fetch = FetchType.LAZY)
//	@Lazy
	private List<AssessmentSalEntity> assessmentSals;

	@JsonView
	@OneToMany(mappedBy = "sal")
//	@Basic(fetch = FetchType.LAZY)
//	@JsonIgnoreProperties
//	@Lazy
	private List<QuestionHasSalEntity> questionHasSals;

	public SalEntity(){

	}

	public SalEntity(Long id, String value) {
		super.id = id;
		this.value = value;
	}

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