package constants.users;

import com.payAm.core.constant.BaseConstants;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/May/27 - 11:17
 */
public interface TokenActionConstants extends BaseConstants {

    String ENTITY = "tokenAction";

    long VERIFICATION_TIME = 7 * 24 * 3600;

    String TOKEN = "token";
    String ERROR_TOKEN_MAX_LENGTH = "error.tokenAction.token.maxLength";
    String USER = "user";
    String TYPE = "type";
    String ERROR_TYPE_REQUIRED = "error.tokenAction.type.required";
    String CREATED = "created";
    String ERROR_CREATED_REQUIRED = "error.tokenAction.created.required";
    String EXPIRES = "expires";
    String ERROR_EXPIRES_REQUIRED = "error.tokenAction.expires.required";

}