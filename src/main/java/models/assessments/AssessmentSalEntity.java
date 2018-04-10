package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.AssessmentSalConstants;
import dtos.assessments.AssessmentSalView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="assessment_sal")
@EntityListeners({AuditingEntityListener.class})
public class AssessmentSalEntity extends BaseEntity implements AssessmentSalConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(AssessmentSalView.class)
	@ManyToOne
	@JoinColumn(name = "sal_id")
	private SalEntity sal;

	@JsonView
	@OneToMany(mappedBy = "assessmentSal")
	private List<OrganizationAssessmentEntity> organizationAssessments;

	@JsonView(AssessmentSalView.class)
	@Size(max = 45)
	private String accessibility;

	@JsonView(AssessmentSalView.class)
	@Column(name="capital_asset")
	private Float capitalAsset;

	@JsonView(AssessmentSalView.class)
	@Size(max = 45)
	private String confidentiality;

	@JsonView(AssessmentSalView.class)
	private Integer death;

	@JsonView(AssessmentSalView.class)
	@Column(name="economy_impact")
	private Float economyImpact;

	@JsonView(AssessmentSalView.class)
	@Column(name="environmental_clean_up")
	private Float environmentalCleanUp;

	@JsonView(AssessmentSalView.class)
	private Integer hospital;

	@JsonView(AssessmentSalView.class)
	private Integer injury;

	@JsonView(AssessmentSalView.class)
	@Size(max = 45)
	private String integrity;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public SalEntity getSal() {
		return sal;
	}

	public void setSal(SalEntity sal) {
		this.sal = sal;
	}

	public List<OrganizationAssessmentEntity> getOrganizationAssessments() {
		return organizationAssessments;
	}

	public void setOrganizationAssessments(List<OrganizationAssessmentEntity> organizationAssessments) {
		this.organizationAssessments = organizationAssessments;
	}

	public String getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}

	public Float getCapitalAsset() {
		return capitalAsset;
	}

	public void setCapitalAsset(Float capitalAsset) {
		this.capitalAsset = capitalAsset;
	}

	public String getConfidentiality() {
		return confidentiality;
	}

	public void setConfidentiality(String confidentiality) {
		this.confidentiality = confidentiality;
	}

	public Integer getDeath() {
		return death;
	}

	public void setDeath(Integer death) {
		this.death = death;
	}

	public Float getEconomyImpact() {
		return economyImpact;
	}

	public void setEconomyImpact(Float economyImpact) {
		this.economyImpact = economyImpact;
	}

	public Float getEnvironmentalCleanUp() {
		return environmentalCleanUp;
	}

	public void setEnvironmentalCleanUp(Float environmentalCleanUp) {
		this.environmentalCleanUp = environmentalCleanUp;
	}

	public Integer getHospital() {
		return hospital;
	}

	public void setHospital(Integer hospital) {
		this.hospital = hospital;
	}

	public Integer getInjury() {
		return injury;
	}

	public void setInjury(Integer injury) {
		this.injury = injury;
	}

	public String getIntegrity() {
		return integrity;
	}

	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}
}