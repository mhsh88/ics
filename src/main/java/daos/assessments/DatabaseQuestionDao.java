package daos.assessments;

import com.payAm.core.ebean.BaseDao;
import models.assessments.DatabaseQuestionEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Dec/08 - 14:03
 */

@Singleton
public class DatabaseQuestionDao extends BaseDao<DatabaseQuestionEntity,Long> {
}