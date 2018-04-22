package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.PreQuestionConstants;
import dtos.assessments.PreQuestionView;
import dtos.assessments.QuestionHasSalView;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;

@Entity
@Table(name="pre_question")
@EntityListeners({AuditingEntityListener.class})
public class PreQuestionEntity extends BaseEntity implements PreQuestionConstants {
	private static final long serialVersionUID = 1L;

	@JsonView({PreQuestionView.class, QuestionHasSalView.class})
	@Lob
	public String text;

	@JsonView
	@OneToMany(mappedBy = "preQuestion")
//	@Basic(fetch = FetchType.LAZY)
//    @Lazy
    public List<QuestionHasSalEntity> questionHasSals;

	@JsonView
	@OneToMany(mappedBy = "preQuestion")
//	@Basic(fetch = FetchType.LAZY)
//    @Lazy
	public List<PreQuestionAnswerEntity> preQuestionAnswers;

	public PreQuestionEntity(){}

	public PreQuestionEntity(Long id, String text) {
		super.id = id;
		this.text = text;
	}

}