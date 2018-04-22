package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.QuestionConstants;
import dtos.assessments.QuestionHasSalView;
import dtos.assessments.QuestionView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@EntityListeners({AuditingEntityListener.class})
public class QuestionEntity extends BaseEntity implements QuestionConstants {
	private static final long serialVersionUID = 1L;

	@JsonView
	@OneToMany(mappedBy = "question")
	@Basic(fetch = FetchType.LAZY)
	public List<QuestionHasSalEntity> questionHasSals;

	@JsonView
	@ManyToMany
	@JoinTable(
			name="question_has_scope",
			joinColumns={@JoinColumn(name="question_id", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="scope_id", nullable=false)}
	)
	@Basic(fetch = FetchType.LAZY)
	public List<QuestionScopeEntity> questionScopes;

	@JsonView({QuestionView.class, QuestionHasSalView.class})
	@Lob
	public String text;

	@JsonView
	@OneToMany(mappedBy = "question")
	@Basic(fetch = FetchType.LAZY)
	public List<QuestionAnswerEntity> questionAnswers;
}