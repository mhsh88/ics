package models.assessments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.assessments.SubMetricConstants;
import dtos.assessments.QuestionHasSalView;
import dtos.assessments.SubMetricView;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sub_metric")
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubMetricEntity extends BaseEntity implements SubMetricConstants {
    private static final long serialVersionUID = 1L;

    @JsonView({SubMetricView.class, QuestionHasSalView.class})
    @Lob
    public String text;

    @JsonView
    @OneToMany(mappedBy = "metric")
//    @Basic(fetch = FetchType.LAZY)
//    @Lazy
    public List<QuestionHasSalEntity> questionHasSals;


//    @PersistenceConstructor
    public SubMetricEntity() {
    }

    public SubMetricEntity(Long id){
        super.id = id;
    }


    public SubMetricEntity(Long id, String text) {
        super.id = id;
        this.text = text;
    }

    public SubMetricEntity(String subMetric) {
        super();
        this.text = subMetric;
    }
}