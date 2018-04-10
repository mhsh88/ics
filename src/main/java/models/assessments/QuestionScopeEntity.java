package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.QuestionScopeConstants;
import dtos.assessments.QuestionScopeView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="question_scope")
@EntityListeners({AuditingEntityListener.class})
public class QuestionScopeEntity extends BaseEntity implements QuestionScopeConstants {
    private static final long serialVersionUID = 1L;

    @JsonView(QuestionScopeView.class)
    @Size(max = 45)
    public String value;

    @JsonView
    @ManyToMany(mappedBy = "questionScopes")
    public List<QuestionEntity> questions;
}
