package com.payAm.core.ebean;


//import com.avaje.ebean.Expr;
//import com.avaje.ebean.ExpressionList;
//import com.avaje.ebean.Junction;
//import com.avaje.ebean.Query;
//import com.avaje.ebean.text.PathProperties;
import com.google.common.base.CaseFormat;
import com.payAm.core.dto.FilterDto;
import com.payAm.core.dto.PageDto;
import com.payAm.core.dto.PaginationDto;
import com.payAm.core.dto.SorterDto;
import com.payAm.core.i18n.CoreMessagesCodes;
import com.payAm.core.model.BaseEntity;
import com.payAm.core.util.StringUtil;
import org.hibernate.sql.Select;
//import com.payAm.core.util.FilterOperatorUtil;
//import com.payAm.core.util.StringUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.T;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;


public abstract class BaseDao<I extends Serializable, E extends BaseEntity> {

    @PersistenceContext
    EntityManager entityManager;


    public PageResult<E> find(PageDto page) throws Exception {
        PageResult<E> pageResult = new PageResult<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<E> queri = criteriaBuilder.createQuery(getEntityClass());

        Root<E> entityRoot = queri.from(getEntityClass());

        queri = addPathProperties(queri, entityRoot, page.getFetchFields());

        queri = filterResults(queri,criteriaBuilder, entityRoot,  page.getFilters());

        queri = resultOrder(queri,criteriaBuilder, entityRoot,page.getSort());


        TypedQuery<E> qry = entityManager.createQuery(queri);
//        int sfa = qry.getMaxResults();
//        qry.
        if (page.isEnablePaging()) {
            pageResult.setTotal(qry.getResultList().size());
        }
        qry = addPagination(qry, page.getPagination());


        List<E> entities = qry.getResultList();
        pageResult.setData(entities);
        pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODELS);
        return pageResult;
    }

    private CriteriaQuery<E> filterResults(CriteriaQuery<E> queri, CriteriaBuilder criteriaBuilder, Root<E> entityRoot, List<FilterDto> filters) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for(FilterDto filter : filters) {
            switch(filter.getOperator()){
                case EQ:
                    if(filter.getField().equals("deleted"))
                        predicates.add(criteriaBuilder.equal(entityRoot.get(filter.getField()), Boolean.parseBoolean(filter.getValue())));

                    else
                        predicates.add(criteriaBuilder.equal(entityRoot.get(filter.getField()), Boolean.parseBoolean(filter.getValue())));
                    break;
                case NE:
                        predicates.add(criteriaBuilder.notEqual(entityRoot.get(filter.getField()), filter.getValue()));
//                    cb.notEqual(area, areaParam)
                    break;
                case GT:

                    predicates.add(isCreatable(filter.getValue()) ?
                            criteriaBuilder.gt(entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
                            criteriaBuilder.greaterThan(entityRoot.get(filter.getField()), filter.getValue()));
//                    cb.greaterThan(name, nameParam)
//                    cb.gt(area, areaParam)
                    break;
                case GE:
//                    cb.greaterThanOrEqualTo(name, nameParam)
//                    cb.ge(area, areaParam);
                    predicates.add(isCreatable(filter.getValue()) ?
                            criteriaBuilder.ge(entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
                            criteriaBuilder.greaterThanOrEqualTo(entityRoot.get(filter.getField()), filter.getValue()));
                    break;
                case LT:

                    predicates.add(isCreatable(filter.getValue()) ?
                            criteriaBuilder.lt(entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
                            criteriaBuilder.lessThan(entityRoot.get(filter.getField()), filter.getValue()));
//                    cb.lt(area, areaParam)
                    break;
                case LE:
//                    String field = filter.getField();
//                    String value = filter.getValue();
                    predicates.add(isCreatable(filter.getValue()) ?
                            criteriaBuilder.le(entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
                            criteriaBuilder.lessThanOrEqualTo(entityRoot.get(filter.getField()), filter.getValue()));
//                    cb.le(area, areaParam)
                    break;
                case IS_NULL:
//                    cb.isNull(name)
                    break;

                case IS_NOT_NULL:
//                    cb.isNotNull(name)
                    break;
                case LIKE:

//                    Predicate l1 = cb.like(path, param);
//                    Predicate l2 = cb.like(path, "a%");
                    predicates.add(criteriaBuilder.like(entityRoot.get(filter.getField()), StringUtil.PERCENT + filter.getValue() + StringUtil.PERCENT));



                    break;
                case STARTS_WITH:
                    break;
                case ENDS_WITH:
                    break;
                case IN:
                    break;
                case JSON_EQ:
                    break;
                case JSON_EXISTS:
                    break;
                case JSON_NE:
                    break;
                case JSON_NOT_EXISTS:
                    break;
                case AND:
//                    and();
                    break;
                case OR:
                    break;
                case NOT:
                    break;
                default:
                    //code to be executed if all cases are not matched;
            }

        }
        return queri.where(predicates.toArray(new Predicate[]{}));
    }

    private CriteriaQuery<E> resultOrder(CriteriaQuery<E> queri, CriteriaBuilder criteriaBuilder, Root<E> entityRoot, SorterDto sort) {
//        if(sort.getOrder().equals())
        return sort.getOrder().equals("ASC") ?
                queri.orderBy(criteriaBuilder.asc(entityRoot.get(sort.getField()))) :
                queri.orderBy(criteriaBuilder.desc(entityRoot.get(sort.getField())));

    }

    private TypedQuery<E> addPagination(TypedQuery<E> qry, PaginationDto pagination) {
//        pagination.getPageNumber();

        return qry.setFirstResult((pagination.getPageNumber() - 1) * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize());
                /*.setMaxResults((pagination.getPageNumber() * pagination.getPageSize()) - 1)*/
    }


    private CriteriaQuery<E> addPathProperties(CriteriaQuery<E> query, Root<E> entityRoot, List<String> fields) throws ClassNotFoundException {
        List<Selection<?>> properties = new ArrayList<>();
//        List<Path<Object>> adsf = fields.stream()
//                .map(f -> entityRoot.get(f))
//                .collect(Collectors.toList());
        List<String> list = new ArrayList<>();
        for (String fullAssociationPath : fields) {

            if(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, fullAssociationPath).equals(fullAssociationPath.toLowerCase())){

                list.add(fullAssociationPath);

            }
            else {
                String str = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fullAssociationPath);
                String temp = str.substring(0,str.length()-1) + "Entity";
//                Class<?> cls = Class.forName(temp);
            }
//            list.add(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, fullAssociationPath));
//            list.add(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fullAssociationPath));

        }
        return query.multiselect(list.stream()
                .map(f -> entityRoot.get(f))
                .collect(Collectors.toList()));
        
        
//
  
//
//               if (fullAssociationPath.contains(StringUtil.DOT)) {
//                   String path = fullAssociationPath.substring(0, fullAssociationPath.lastIndexOf('.'));
//                   String property = fullAssociationPath.substring(fullAssociationPath.lastIndexOf('.') + 1);
////                   pathProperties.addToPath(path, property);
//               }
//               else {
//                    cq.multiselect(this.fields.stream()
//                           .map(f -> root.get(f.getName()))
//                           .collect(Collectors.toList()));
//                   properties.add(entityRoot.get(fullAssociationPath));
//               }
//
//
//        }
//        return query.multiselect(properties);

    }




    private Class<E> getEntityClass() {
        Class<?> clazz = getClass();
        while (clazz.getGenericSuperclass() != null) {
            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                return (Class<E>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[1];
            } else {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}