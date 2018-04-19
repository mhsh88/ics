package daos.users;

//import com.avaje.ebean.Ebean;
//import com.avaje.ebean.QueryIterator;
import com.payAm.core.ebean.BaseDao;
//import enumerations.security.ActionTokenType;
import models.users.TokenActionEntity;
import org.springframework.stereotype.Repository;
//import models.users.UserEntity;

import javax.inject.Singleton;
//import java.sql.Timestamp;
//import java.util.UUID;

/**
 * Created by Payam Mostafaei
 * Creation time: 2017/May/17 - 8:36 AM
 */

@Singleton
public class TokenActionDao extends BaseDao<Long, TokenActionEntity> {
//
//    public TokenActionEntity findByToken(final String token, final ActionTokenType type) {
//        return super.where()
//                .eq("token", token)
//                .eq("type", type)
//                .findUnique();
//    }
//
//    public void deleteByUser(final UserEntity u, final ActionTokenType type) {
//        QueryIterator<TokenActionEntity> iterator = super.where()
//                .eq("user.id", u.id)
//                .eq("type", type)
//                .findIterate();
//        Ebean.delete(iterator);
//        iterator.close();
//    }
//
//    public TokenActionEntity create(final ActionTokenType type, final String token, final UserEntity user) {
//        final TokenActionEntity ta = new TokenActionEntity();
//        ta.user = user;
//        ta.token = token;
//        ta.type = type;
//        final Timestamp created = new Timestamp(System.currentTimeMillis());
//        ta.created = created;
//        ta.expires = new Timestamp(created.getTime() + TokenActionEntity.VERIFICATION_TIME * 1000);
//        ta.save();
//        return ta;
//    }
//
//    public static String generateToken() {
//        return UUID.randomUUID().toString();
//    }
}
