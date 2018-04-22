package daos.assessments;

import com.payAm.core.ebean.BaseDao;
import models.assessments.QuestionAnswerEntity;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:03
 */

@Singleton
public class QuestionAnswerDao extends BaseDao<QuestionAnswerEntity,Long> {
}