package com.payAm.core.validator;//package com.payAm.core.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.FIELD;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Feb/07 - 10:34 PM
// */
//@Target({FIELD})
//@Retention(RUNTIME)
//@Constraint(validatedBy = EmailOrMobileNumberValidator.class)
//@play.data.Form.Display(name="constraint.emailOrMobileNumber")
//public @interface EmailOrMobileNumber {
//    String message() default EmailOrMobileNumberValidator.message;
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
