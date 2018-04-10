package com.payAm.core.validator;//package com.payAm.core.validator;
//
//import play.data.validation.Constraints;
//import play.libs.F;
//
//import javax.validation.ConstraintValidator;
//
//import static play.libs.F.Tuple;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Feb/07 - 10:35 PM
// */
//public class EmailOrMobileNumberValidator
//        extends Constraints.Validator<String>
//        implements ConstraintValidator<EmailOrMobileNumber, String> {
//
//    public final static String message = "error.validator.emailOrMobileNumber";
//    private final static Constraints.EmailValidator emailValidator = new Constraints.EmailValidator();
//    private final static MobileNumberValidator mobileNumberValidator = new MobileNumberValidator();
//
//    @Override
//    public void initialize(EmailOrMobileNumber constraintAnnotation) { }
//
//    @Override
//    public boolean isValid(String object) {
//        if(object == null || object.length() == 0) {
//            return true;
//        }
//
//        return emailValidator.isValid(object) || mobileNumberValidator.isValid(object);
//    }
//
//    @Override
//    public F.Tuple<String, Object[]> getErrorMessageKey() {
//        return Tuple(message, new Object[] {});
//    }
//}
