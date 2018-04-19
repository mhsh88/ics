package daos.assessments;

import com.payAm.core.ebean.BaseDao;
import models.assessments.SubMetricEntity;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2018/Jan/10 - 00:03
 */

@Singleton
public class SubMetricDao extends BaseDao<Long, SubMetricEntity> {
}