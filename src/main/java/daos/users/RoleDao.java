package daos.users;

import com.payAm.core.ebean.BaseDao;
import models.users.RoleEntity;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/Feb/09 - 10:00
 */

@Singleton
public class RoleDao extends BaseDao<Long, RoleEntity> {
}