package models.assessments;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.SubMetricConstants;
import dtos.assessments.QuestionHasSalView;
import dtos.assessments.SubMetricView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sub_metric")
@EntityListeners({AuditingEntityListener.class})
public class SubMetricEntity extends BaseEntity implements SubMetricConstants {
    private static final long serialVersionUID = 1L;

    @JsonView({SubMetricView.class, QuestionHasSalView.class})
    @Lob
    public String text;

    @JsonView
    @OneToMany(mappedBy = "metric")
    public List<QuestionHasSalEntity> questionHasSals;
}