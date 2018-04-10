package constants.users;

import com.payAm.core.constant.BaseConstants;

/**
 * Developer: Payam Mostafaei
 * Creation Time: 2017/May/27 - 11:17
 */
public interface RoleConstants extends BaseConstants {

    String ENTITY = "role";

    String NAME = "name";
    String ERROR_NAME_REQUIRED = "error.securityRole.name.required";
    String ERROR_NAME_MAX_LENGTH = "error.securityRole.name.maxLength";
    String TITLE = "title";
    String ERROR_TITLE_REQUIRED = "error.securityRole.title.required";
    String PERMISSIONS = "permissions";
    String USERS = "users";

}