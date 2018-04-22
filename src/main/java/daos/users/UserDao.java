package daos.users;

import com.payAm.core.ebean.BaseDao;
import models.users.UserEntity;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;

//
//import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
//import com.feth.play.module.pa.user.*;
//import com.payAm.core.ebean.BaseDao;
//import enumerations.security.ActionTokenType;
//import models.users.LinkedAccountEntity;
//import models.users.UserEntity;
//import security.structure.MobileIdentity;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
///**
// * Developer: Payam Mostafaei
// * Creation Time: 2017/Feb/09 - 10:00
// */
//
@Singleton
public class UserDao extends BaseDao<UserEntity, Long> {
}

//
//    @Inject
//    private TokenActionDao tokenActionDao;
//
//    @Inject
//    private LinkedAccountDao linkedAccountDao;
//
//    public boolean existsByAuthUserIdentity(final AuthUserIdentity identity) {
//        if (identity instanceof UsernamePasswordAuthUser) {
//            return where()
//                    .eq("active", true)
//                    .eq("username", ((UsernamePasswordAuthUser) identity).getEmail())
//                    .eq("linkedAccounts.providerKey", identity.getProvider())
//                    .findRowCount() > 0;
//        }
//
//
//        return where()
//                .eq("active", true)
//                .eq("linkedAccounts.providerUserId", identity.getId())
//                .eq("linkedAccounts.providerKey", identity.getProvider())
//                .findRowCount() > 0;
//    }
//
//    public UserEntity findByAuthUserIdentity(final AuthUserIdentity identity) {
//        if (identity == null) return null;
//        if (identity instanceof UsernamePasswordAuthUser) {
//            return where()
//                    .eq("active", true)
//                    .eq("username", ((UsernamePasswordAuthUser) identity).getEmail())
//                    .eq("linkedAccounts.providerKey", identity.getProvider())
//                    .findUnique();
//        }
//
//        return where()
//                .eq("active", true)
//                .eq("linkedAccounts.providerUserId", identity.getId())
//                .eq("linkedAccounts.providerKey", identity.getProvider())
//                .findUnique();
//    }
//
//    public UserEntity findByUsernamePasswordIdentity(final UsernamePasswordAuthUser identity) {
//        return where()
//                .eq("active", true)
//                .eq("username", identity.getEmail())
//                .eq("linkedAccounts.providerKey", identity.getProvider())
//                .findUnique();
//    }
//
//    public UserEntity findByUsernamePassword(final UserEntity user) {
//        return where()
//                .eq("active", true)
//                .eq("username", user.username)
//                .eq("password", user.password)
//                .findUnique();
//    }
//
//    public UserEntity create(final AuthUser authUser)/* throws NameIdentityNotSupported*/ {
//        final UserEntity user = new UserEntity();
//        user.active = true;
//        //user.securityRoles = Collections.singletonList(SecurityRole.findByRoleName(RoleType.GUEST));
//        //user.linkedAccounts = Collections.singletonList(LinkedAccount.create(authUser));
//
//        /*if (authUser instanceof GoogleAuthUser) {
//            String language = ((GoogleAuthUser) authUser).getLocale().getLanguage();
//            if (Play.application().configuration().getStringList("play.i18n.langs").contains(language))
//                user.language = ((GoogleAuthUser) authUser).getLocale().getLanguage();
//            else user.language = "en";
//        }
//
//        if (authUser instanceof AppUsernamePasswordAuthUser)
//            user.language = ((AppUsernamePasswordAuthUser) authUser).getLanguage();*/
//
//        if (authUser instanceof EmailIdentity && ((EmailIdentity) authUser).getEmail() != null) {
//            final EmailIdentity identity = (EmailIdentity) authUser;
//            // Remember, even when getting them from FB & Co., emails should be
//            // verified within the application as a security breach there might
//            // break your security as well!
//            user.email = identity.getEmail();
//            user.emailActivated = false;
//        }
//
//        if (authUser instanceof MobileIdentity && ((MobileIdentity) authUser).getPhoneNumber() != null) {
//            final MobileIdentity identity = (MobileIdentity) authUser;
//            // Remember, even when getting them from FB & Co., emails should be
//            // verified within the application as a security breach there might
//            // break your security as well!
//            user.phoneNumber = identity.getPhoneNumber();
//            user.mobileActivated = false;
//        }
//
//        if (authUser instanceof NameIdentity) {
//            //throw new NameIdentityNotSupported(messages.at(CoreMessagesCodes.ERROR_NAME_IDENTITY_NOT_SUPPORTED));
//        }
//
//        if (authUser instanceof FirstLastNameIdentity) {
//            final FirstLastNameIdentity identity = (FirstLastNameIdentity) authUser;
//            final String firstName = identity.getFirstName();
//            final String lastName = identity.getLastName();
//            if (firstName != null) user.firstName = firstName;
//            if (lastName != null) user.lastName = lastName;
//        }
//        // Ebean.saveManyToManyAssociations(user, "roles");
//        // Ebean.saveManyToManyAssociations(user, "permissions");
//        return insert(user);
//    }
//
//    public void merge(final AuthUser oldUser, final AuthUser newUser) {
//        merge(findByAuthUserIdentity(oldUser), findByAuthUserIdentity(newUser));
//    }
//
//    public void merge(UserEntity user, final UserEntity otherUser) {
//        for (final LinkedAccountEntity acc : otherUser.linkedAccounts) {
//            final LinkedAccountEntity ret = new LinkedAccountEntity();
//            ret.providerKey = acc.providerKey;
//            ret.providerUserId = acc.providerUserId;
//            user.linkedAccounts.add(ret);
//        }
//        // do all other merging stuff here - like resources, etc.
//
//        // deactivate the merged user that got added to this one
//        otherUser.logicallyDelete();
//        update(otherUser);
//        update(user);
//    }
//
//    public void addLinkedAccount(final AuthUser oldUser, final AuthUser newUser) {
//        final UserEntity u = findByAuthUserIdentity(oldUser);
//        final LinkedAccountEntity ret = new LinkedAccountEntity();
//        ret.providerKey = newUser.getProvider();
//        ret.providerUserId = newUser.getId();
//        u.linkedAccounts.add(ret);
//        update(u);
//    }
//
//    public UserEntity findByUsername(final String username) {
//        return where().eq("active", true).eq("username", username).findUnique();
//    }
//
//    public void verify(final UserEntity unverified) {
//        unverified.emailActivated = true;
//        update(unverified);
//        tokenActionDao.deleteByUser(unverified, ActionTokenType.EMAIL_VERIFICATION);
//    }
//
//    public int getCount() {
//        return findRowCount();
//    }
//
//    public void changePassword(final UsernamePasswordAuthUser authUser, final boolean create) {
//        UserEntity user = UserEntity.toAuthUser(authUser);
//        LinkedAccountEntity a = linkedAccountDao.findByProviderKey(authUser.getEmail(), authUser.getProvider());
//        if (a == null) {
//            if (create) {
//                a = linkedAccountDao.create(authUser);
//                a.user = user;
//            } else {
//                throw new RuntimeException("Account not enabled for password usage");
//            }
//        }
//        a.providerUserId = authUser.getHashedPassword();
//        a.save();
//    }
//
//    public void resetPassword(final UsernamePasswordAuthUser authUser, final boolean create) {
//        UserEntity user = UserEntity.toAuthUser(authUser);
//        this.changePassword(authUser, create);
//        tokenActionDao.deleteByUser(user, ActionTokenType.PASSWORD_RESET);
//    }
//}