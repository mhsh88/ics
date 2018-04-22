package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.QuestionScopeConstants;
import dtos.assessments.QuestionScopeView;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
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
//    @Lazy
    public List<QuestionEntity> questions;

    public QuestionScopeEntity(){

    }

    public QuestionScopeEntity(Long id, String value) {
        super.id = id;
        this.value = value;
    }

    public QuestionScopeEntity(Long id, String value, Collection<QuestionEntity> questions) {
        this(id, value);
        this.questions = (List<QuestionEntity>) questions;
    }
}
