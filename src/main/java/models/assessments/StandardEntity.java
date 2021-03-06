package models.assessments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.StandardConstants;
import dtos.assessments.QuestionHasSalView;
import dtos.assessments.StandardView;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "standard")
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class StandardEntity extends BaseEntity implements StandardConstants {
	private static final long serialVersionUID = 1L;

	@JsonView({StandardView.class, QuestionHasSalView.class})
	@Lob
	public String text;

	@JsonView
	@ManyToMany(mappedBy = "standards")
//	@Basic(fetch = FetchType.LAZY)
//    @Lazy
	public List<OrganizationAssessmentEntity> organizationAssessments;

	@JsonView
	@OneToMany(mappedBy = "standard")
//	@Basic(fetch = FetchType.LAZY)
//    @Lazy
	public List<QuestionHasSalEntity> questionHasSals;

	public StandardEntity(){}

    public StandardEntity(Long id, String text) {
        super.id = id;
        this.text = text;
    }

    public StandardEntity(String standard) {
        super();
        this.text = standard;
    }
}