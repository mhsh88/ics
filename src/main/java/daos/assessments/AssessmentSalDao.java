package daos.assessments;

import com.payAm.core.ebean.BaseDao;
import models.assessments.AssessmentSalEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import javax.persistence.PersistenceContext;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:03
 */

@Singleton
//@Service
public class AssessmentSalDao extends BaseDao<AssessmentSalEntity, Long> {
}