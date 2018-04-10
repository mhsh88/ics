package constants.users;

import com.payAm.core.constant.BaseConstants;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/May/27 - 11:17
 */
public interface LinkedAccountConstants extends BaseConstants {

    String ENTITY = "linkedAccount";

    String USER = "user";
    String PROVIDER_USER_ID = "providerUserId";
    String ERROR_PROVIDER_USER_ID_REQUIRED = "error.linkedAccount.providerUserId.required";
    String ERROR_PROVIDER_USER_ID_MAX_LENGTH = "error.linkedAccount.providerUserId.maxLength";
    String PROVIDER_KEY = "providerKey";
    String ERROR_PROVIDER_KEY_REQUIRED = "error.linkedAccount.providerKey.required";
    String ERROR_PROVIDER_KEY_MAX_LENGTH = "error.linkedAccount.providerKey.maxLength";

}