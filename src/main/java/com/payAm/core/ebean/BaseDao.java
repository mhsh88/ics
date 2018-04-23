package com.payAm.core.ebean;


//import com.avaje.ebean.Expr;
//import com.avaje.ebean.ExpressionList;
//import com.avaje.ebean.Junction;
//import com.avaje.ebean.Query;
//import com.avaje.ebean.text.PathProperties;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Constant;
import com.mysema.query.types.Ops;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.path.SimplePath;
import com.payAm.core.dto.FilterDto;
import com.payAm.core.dto.PageDto;
import com.payAm.core.dto.PaginationDto;
import com.payAm.core.dto.SorterDto;
import com.payAm.core.i18n.CoreMessagesCodes;
import com.payAm.core.model.BaseEntity;
import com.payAm.core.model.QBaseEntity;
import com.payAm.core.util.StringUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javafx.animation.PathTransitionBuilder;
import models.assessments.QSalEntity;
import models.assessments.QSubMetricEntity;
import models.assessments.SubMetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

//import com.payAm.core.util.FilterOperatorUtil;
//import com.payAm.core.util.StringUtil;

//@org.springframework.stereotype.Repository
//@Transactional

@Primary
public abstract class BaseDao<T extends BaseEntity, ID extends Serializable>  {

    @PersistenceContext
    EntityManager entityManager;


    private QueryDslJpaRepository<T, ID> repository;


    public List<T> getModels(PageDto page) throws Exception{


        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QSubMetricEntity qSubMetricEntity = QSubMetricEntity.subMetricEntity;
        List<SubMetricEntity> sd = queryFactory.selectFrom(qSubMetricEntity).fetch();

        return (List<T>) sd;


//        Class<?> asdf = Class.forName("Q" + getEntityClass().getSimpleName().toString(), true, ClassLoader.getSystemClassLoader());

//        new QBaseEntity("baseEntity");
//        List<T> c = queryFactory.selectFrom(getEntityClass())
//                .orderBy(user.login.asc())
//                .fetch();

//        QBaseEntity qBaseEntity = getQtypeOfEntity();
//        List<Filter> filters, Sorter sorter, int page, int limit, boolean isOr
//        System.out.println("Q"+getEntityClass().getSimpleName().toString()+StringUtil.DOT+getEntityClass().getSimpleName().toString().toLowerCase());
//        Class.forName("Q"+getEntityClass().getSimpleName().toString()+StringUtil.DOT+getEntityClass().getSimpleName().toString().toLowerCase());
//        T model = Class.forName("Q"+getEntityClass().getSimpleName().toString()+StringUtil.DOT+getEntityClass().getSimpleName().toString().toLowerCase(), true,)
//        QBaseEntity model = QBaseEntity.baseEntity;
//        BaseEntity baseEntity= queryFactory.selec
//        List<T> c = queryFactory.selectFrom(user)
//                .orderBy(user.login.asc())
//                .fetch();
//        Sort sort;
//        if (page.getSort().getOrder() == null)
//            sort = new Sort(new Sort.Order(Sort.Direction.ASC, page.getFetchFields().get(1)), new Sort.Order(Sort.Direction.ASC, page.getFetchFields().get(0)));
//        else
//            sort = new Sort(page.getSort().getOrder().toLowerCase().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, page.getSort().getField());
//
//        Pageable pageSpecification = new PageRequest(page.getPagination().getPageNumber() - 1, page.getPagination().getPageSize(), sort);
//
//        BooleanBuilder predicate = new BooleanBuilder();
//        Path<T> model = Expressions.path(Class.forName(getEntityClass()), "*");
//
//        SimplePath<Boolean> deleted = Expressions.path(Boolean.class, (com.mysema.query.types.Path<?>) model, BaseEntity.DELETED);
//        Constant<Boolean> falseValue = (Constant<Boolean>) Expressions.constant(false);
//
//        if (page.getFilters() != null)
//            for (FilterDto filter : page.getFilters()) {
//                SimplePath<String> field = Expressions.path(String.class, model, filter.getField());
//                Constant<String> value = (Constant<String>) Expressions.constant(filter.getValue());
////                if (page.getAdvancedFilter()) predicate.or(Expressions.predicate(filter.getOperator().getCode(), field, value));
////                else predicate.and(Expressions.predicate(filter.getOperator(), field, value));
//            }
//
//        predicate.and(Expressions.predicate(Ops.EQ, deleted, falseValue));

//        return repository.findAll();//predicate, pageSpecification).getContent();
    }



    public PageResult<T> findData(PageDto page) throws Exception {
        List<T> lsit =  getModels(page);
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setData(lsit);
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<T> queri = criteriaBuilder.createQuery(getEntityClass());
//
//        Root<T> entityRoot = queri.from(getEntityClass());
//
//        queri = addPathProperties(queri, entityRoot, page.getFetchFields());
//
//        queri = filterResults(queri, criteriaBuilder, entityRoot, page.getFilters());
//
//        queri = resultOrder(queri, criteriaBuilder, entityRoot, page.getSort());
//
//
//        TypedQuery<T> qry = entityManager.createQuery(queri);
////        int sfa = qry.getMaxResults();
////        qry.
//        if (page.isEnablePaging()) {
//            pageResult.setTotal(qry.getResultList().size());
//        }
//        qry = addPagination(qry, page.getPagination());
//
//
//        List<T> entities = qry.getResultList();
//        pageResult.setData(entities);
        pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODELS);
        return pageResult;
    }

    private CriteriaQuery<T> filterResults(CriteriaQuery<T> queri, CriteriaBuilder criteriaBuilder, Root<T> entityRoot, List<FilterDto> filters) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (FilterDto filter : filters) {

            switch (filter.getOperator()) {
                case EQ:


                    if (filter.getField().equals(BaseEntity.DELETED))
                        predicates.add(criteriaBuilder.equal(entityRoot.get(changeToColumnName(filter.getField())), Boolean.parseBoolean(filter.getValue())));

                    else
                        predicates.add(criteriaBuilder.equal(entityRoot.get(changeToColumnName(filter.getField())), Boolean.parseBoolean(filter.getValue())));
                    break;
                case NE:
                    predicates.add(criteriaBuilder.notEqual(entityRoot.get(changeToColumnName(filter.getField())), filter.getValue()));
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

    private String changeToColumnName(String field) {
        if (field.contains(StringUtil.DOT)) {
            String path = field.substring(0, field.lastIndexOf('.'));
            String property = field.substring(field.lastIndexOf('.') + 1);
            return path + StringUtil.UNDER_LINE + property;
        }
        return field;
    }

    private CriteriaQuery<T> resultOrder(CriteriaQuery<T> queri, CriteriaBuilder criteriaBuilder, Root<T> entityRoot, SorterDto sort) {
//        if(sort.getOrder().equals())
        return sort.getOrder().equals("ASC") ?
                queri.orderBy(criteriaBuilder.asc(entityRoot.get(sort.getField()))) :
                queri.orderBy(criteriaBuilder.desc(entityRoot.get(sort.getField())));

    }

    private TypedQuery<T> addPagination(TypedQuery<T> qry, PaginationDto pagination) {
//        pagination.getPageNumber();

        return qry.setFirstResult((pagination.getPageNumber() - 1) * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize());
        /*.setMaxResults((pagination.getPageNumber() * pagination.getPageSize()) - 1)*/
    }


    private CriteriaQuery<T> addPathProperties(CriteriaQuery<T> query, Root<T> entityRoot, List<String> fields) throws ClassNotFoundException {
        List<Selection<?>> properties = new ArrayList<>();
        return query;
//        List<Path<Object>> adsf = fields.stream()
//                .map(f -> entityRoot.get(f))
//                .collect(Collectors.toList());
//        return query.multiselect(fields.stream()
//                .map(f -> entityRoot.get(f))
//                .collect(Collectors.toList()));
//        List<String> list = new ArrayList<>();
//        for (String fullAssociationPath : fields) {
//
//            if(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, fullAssociationPath).equals(fullAssociationPath.toLowerCase())){
//
//                list.add(fullAssociationPath);
//
//            }
//            else {
//                String str = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fullAssociationPath);
//                String temp = str.substring(0,str.length()-1) + "Entity";
////                Class<?> cls = Class.forName(temp);
//            }
////            list.add(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, fullAssociationPath));
////            list.add(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fullAssociationPath));
//
//        }
////        return query.multiselect(list.stream()
////                .map(f -> entityRoot.get(f))
////                .collect(Collectors.toList()));
//        return query;


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


    private Class<T> getEntityClass() {
        Class<?> clazz = getClass();
        while (clazz.getGenericSuperclass() != null) {
            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
            } else {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}