package constants.users;

import com.payAm.core.constant.BaseConstants;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/May/27 - 11:17
 */
public interface UserConstants extends BaseConstants {

    String ENTITY = "user";

    String ACTIVE = "active";
    String ERROR_ACTIVE_REQUIRED = "error.user.active.required";
    String ACTIVE_YES = "active";
    String ACTIVE_NO = "deactive";
    String ADDRESS = "address";
    String BIRTHDAY = "birthday";
    String ORGANIZATION = "organization";
    String ERROR_BIRTHDAY_REQUIRED = "error.user.birthday.required";
    String CREATED_DATE = "createdDate";
    String ERROR_CREATED_DATE_REQUIRED = "error.user.createdDate.required";
    String DEACTIVATED_REASON = "deactivatedReason";
    String DESCRIPTION = "description";
    String EMAIL = "email";
    String ERROR_EMAIL_MAX_LENGTH = "error.user.email.maxLength";
    String USERNAME = "username";
    String ERROR_USERNAME_REQUIRED = "error.user.username.required";
    String ERROR_USERNAME_MAX_LENGTH = "error.user.username.maxLength";
    String EMAIL_ACTIVATED = "emailActivated";
    String ERROR_EMAIL_ACTIVATED_REQUIRED = "error.user.emailActivated.required";
    String EMAIL_ACTIVATION_CODE = "emailActivationCode";
    String ERROR_EMAIL_ACTIVATION_CODE_MAX_LENGTH = "error.user.emailActivationCode.maxLength";
    String FIRST_NAME = "firstName";
    String ERROR_FIRST_NAME_REQUIRED = "error.user.firstName.required";
    String LAST_NAME = "lastName";
    String ERROR_LAST_NAME_REQUIRED = "error.user.lastName.required";
    String GENDER = "gender";
    String ERROR_GENDER_REQUIRED = "error.user.gender.required";
    String GENDER_MALE = "gender.male";
    String GENDER_FEMALE = "gender.female";
    String INVITE_CODE = "inviteCode";
    String ERROR_INVITE_CODE_MAX_LENGTH = "error.user.inviteCode.maxLength";
    String LOGIN_NOTIFICATION = "loginNotification";
    String ERROR_LOGIN_NOTIFICATION_REQUIRED = "error.user.loginNotification.required";
    String MOBILE_ACTIVATED = "mobileActivated";
    String ERROR_MOBILE_ACTIVATED_REQUIRED = "error.user.mobileActivated.required";
    String MOBILE_ACTIVATION_CODE = "mobileActivationCode";
    String ERROR_MOBILE_ACTIVATION_CODE_MAX_LENGTH = "error.user.mobileActivationCode.maxLength";
    String MOBILE_NUMBER = "mobileNumber";
    String ERROR_MOBILE_NUMBER_MAX_LENGTH = "error.user.mobileNumber.maxLength";
    String PASSWORD = "password";
    String ERROR_PASSWORD_REQUIRED = "error.user.password.required";
    String ERROR_PASSWORD_MAX_LENGTH = "error.user.password.maxLength";
    String PHONE_NUMBER = "phoneNumber";
    String ERROR_PHONE_NUMBER_MAX_LENGTH = "error.user.phoneNumber.maxLength";
    String ROLES = "roles";
    String LINKED_ACCOUNTS = "linkedAccounts";

}