package daos.assessments;

import com.payAm.core.ebean.BaseDao;
import models.assessments.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2018/Jan/10 - 00:03
 */

@Singleton
public class QuestionDao extends BaseDao<Long, QuestionEntity> {
}