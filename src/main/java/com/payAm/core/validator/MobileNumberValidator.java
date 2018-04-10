package com.payAm.core.validator;//package com.payAm.core.validator;
//
//import com.payAm.core.validator.countries.Countries;
//import play.data.validation.Constraints;
//import play.libs.F;
//
//import javax.validation.ConstraintValidator;
//
//import static play.libs.F.Tuple;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Feb/07 - 08:27 PM
// */
//public class MobileNumberValidator extends Constraints.Validator<String> implements ConstraintValidator<MobileNumber, String> {
//
//    public final static String message = "error.validator.mobileNumber";
//    private final static java.util.regex.Pattern regex = java.util.regex.Pattern.compile("^\\+?\\d+$");
//
//    @Override
//    public void initialize(MobileNumber constraintAnnotation) { }
//
//    @Override
//    public boolean isValid(String object) {
//        if(object == null || object.length() == 0) {
//            return true;
//        }
//
//        if (!regex.matcher(object).matches()) {
//            return false;
//        }
//
//        return Countries.COUNTRIES.parallelStream()
//                .map(country -> country.getDigits() + String.valueOf(country.getCallPrefix()).length())
//                .anyMatch(digits -> (object.replace("+", "").length() == digits) ||
//                        (object.startsWith("00") && object.length() == (digits + 2)));
//
//    }
//
//    @Override
//    public F.Tuple<String, Object[]> getErrorMessageKey() {
//        return Tuple(message, new Object[] {});
//    }
//}