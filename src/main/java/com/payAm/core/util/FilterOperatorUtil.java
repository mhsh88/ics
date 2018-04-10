package com.payAm.core.util;//package com.payAm.core.util;
//
//import com.avaje.ebean.Expr;
//import com.avaje.ebean.Junction;
//import com.payAm.core.dto.FilterDto;
//import com.payAm.core.i18n.CoreMessagesCodes;
//import com.payAm.core.model.BaseEntity;
//import play.mvc.Http;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Jan/06 - 03:11 PM
// */
//public class FilterOperatorUtil {
//
//    public static <E extends BaseEntity> Junction<E> addRestriction(Junction<E> junction, FilterDto filter) {
//        switch (filter.getOperator()) {
//            case EQ:
//                junction.add(Expr.ieq(filter.getField(), filter.getValue()));
//                break;
//            case NE:
//                junction.add(Expr.ne(filter.getField(), filter.getValue()));
//                break;
//            case IS_NULL:
//                junction.add(Expr.isNull(filter.getField()));
//                break;
//            case IS_NOT_NULL:
//                junction.add(Expr.isNotNull(filter.getField()));
//                break;
//            case STARTS_WITH:
//                junction.add(Expr.istartsWith(filter.getField(), StringUtil.PERCENT + filter.getValue()));
//                break;
//            case ENDS_WITH:
//                junction.add(Expr.iendsWith(filter.getField(), filter.getValue() + StringUtil.PERCENT));
//                break;
//            case LIKE:
//                junction.add(Expr.ilike(filter.getField(), StringUtil.PERCENT + filter.getValue() + StringUtil.PERCENT));
//                break;
//            case GE:
//                junction.add(Expr.ge(filter.getField(), filter.getValue()));
//                break;
//            case GT:
//                junction.add(Expr.gt(filter.getField(), filter.getValue()));
//                break;
//            case LE:
//                junction.add(Expr.le(filter.getField(), filter.getValue()));
//                break;
//            case LT:
//                junction.add(Expr.lt(filter.getField(), filter.getValue()));
//                break;
//            case IN:
//                junction.add(Expr.in(filter.getField(), filter.getValues()));
//                break;
//            case JSON_EQ:
//                junction.jsonEqualTo(filter.getField(), Http.Context.current().lang().code(), filter.getValue());
//                break;
//            case JSON_EXISTS:
//                junction.jsonExists(filter.getField(), Http.Context.current().lang().code());
//                break;
//            case JSON_NE:
//                junction.jsonNotEqualTo(filter.getField(), Http.Context.current().lang().code(), filter.getValue());
//                break;
//            case JSON_NOT_EXISTS:
//                junction.jsonNotExists(filter.getField(), Http.Context.current().lang().code());
//                break;
//            default:
//                throw new IllegalArgumentException(CoreMessagesCodes.ERROR_ILLEGAL_FILTER_OPERATOR);
//        }
//        return junction;
//    }
//
//}
