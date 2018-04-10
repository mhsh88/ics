package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.OrganizationAssessmentConstants;
import dtos.assessments.OrganizationAssessmentView;
import models.users.UserEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="organization_assessment")
@EntityListeners({AuditingEntityListener.class})
public class OrganizationAssessmentEntity extends BaseEntity implements OrganizationAssessmentConstants {
	private static final long serialVersionUID = 1L;

	@JsonView
	@OneToMany(mappedBy = "organizationAssessment")
	public List<OrganizationAssessmentHasQuestionEntity> organizationAssessmentHasQuestions;

	@JsonView(OrganizationAssessmentView.class)
	@ManyToOne
	@JoinColumn(name = "user_id")
	public UserEntity user;

	@JsonView(OrganizationAssessmentView.class)
	@ManyToOne
	@JoinColumn(name = "assessment_sal_id")
	public AssessmentSalEntity assessmentSal;

	@JsonView
	@ManyToMany
	@JoinTable(
			name = "organization_assessment_has_standard",
			joinColumns = {@JoinColumn(name = "organization_assessment_id", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name = "standard_id", nullable = false)}
	)
	public List<StandardEntity> standards;

	@JsonView(OrganizationAssessmentView.class)
	@Column(name="assessment_name")
	@Size(max = 45)
	public String assessmentName;

	@JsonView(OrganizationAssessmentView.class)
	@Column(name="city_sitename")
	@Size(max = 45)
	public String citySitename;

	@JsonView(OrganizationAssessmentView.class)
	@Column(name="critical_important")
	@Size(max = 45)
	public String criticalImportant;

	@JsonView(OrganizationAssessmentView.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "datetime")
	public Date date;

	@JsonView(OrganizationAssessmentView.class)
	@Column(name="facility_name")
	@Size(max = 45)
	public String facilityName;

	@JsonView(OrganizationAssessmentView.class)
	@Size(max = 45)
	public String industry;

	@JsonView(OrganizationAssessmentView.class)
	@Column(name="IT_OT")
	public String itOt;

	@JsonView(OrganizationAssessmentView.class)
	@Size(max = 45)
	public String sector;

	@JsonView(OrganizationAssessmentView.class)
	@Column(name="state_province_region")
	@Size(max = 45)
	public String stateProvinceRegion;
}