package com.payAm.core.ebean;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Constant;
//import com.mysema.query.support.Expressions;
//import com.mysema.query.types.Constant;
//import com.mysema.query.types.Ops;
//import com.querydsl.core.types.Predicate;
import com.payAm.core.dto.FilterDto;
import com.payAm.core.dto.PageDto;
import com.payAm.core.dto.PaginationDto;
import com.payAm.core.dto.SorterDto;
import com.payAm.core.i18n.CoreMessagesCodes;
import com.payAm.core.model.BaseEntity;
import com.payAm.core.util.StringUtil;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

//import com.payAm.core.util.FilterOperatorUtil;
//import com.payAm.core.util.StringUtil;

//@org.springframework.stereotype.Repository
//@Transactional

//@Primary
@Service
public abstract class BaseDao<T extends BaseEntity, ID extends Serializable>  {

    @PersistenceContext
    EntityManager entityManager;

//    @Autowired
    public QueryDslJpaRepository<T, ID> repository;

    JpaEntityInformation<T, ?> entityInformation;

//    public BaseDao(JpaEntityInformation<BaseEntity, Serializable> entityInformation, EntityManager entityManager) {
//        super(entityInformation, entityManager);
//    }


    public List<T> getModels(PageDto page) throws Exception{
        entityInformation = JpaEntityInformationSupport.getEntityInformation(getEntityClass(), entityManager);
        repository = new QueryDslJpaRepository<T, ID>((JpaEntityInformation<T, ID>) entityInformation, entityManager);
        Sort sort;
        if (page.getSort() == null)
            sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"), new Sort.Order(Sort.Direction.ASC, "value"));
        else
            sort = new Sort(page.getSort().getField().toLowerCase().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, page.getSort().getField());
        Pageable pageSpecification = new PageRequest(page.getPagination().getPageNumber() - 1, page.getPagination().getPageSize(), sort);
    
        BooleanBuilder predicate = new BooleanBuilder();
        String variable = getEntityClass().getSimpleName().substring(0, getEntityClass().getSimpleName().length() - 6);
        SimplePath<T> model = Expressions.path(getEntityClass(), "deleted");
        SimplePath<Boolean> deleted = Expressions.path(Boolean.class, model, "deleted");
        SimplePath<Long> count = Expressions.path(Long.class, model, "deleted");
        Constant<Boolean> falseValue = (Constant<Boolean>) Expressions.constant(false);

        predicate.and(Expressions.predicate(Ops.EQ, deleted,  falseValue));
//        Page<T> firstResult = repository.findAll(predicate, pageSpecificat
        predicate.and(Expressions.predicate(Ops.EQ, count, falseValue));
        int coun = repository.findAll(predicate).size();
        List<T> result = repository.findAll(predicate, pageSpecification).getContent();
        return result;


//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//
//        QSubMetricEntity qSubMetricEntity = QSubMetricEntity.subMetricEntity;
//        queryFactory.selectFrom(qSubMetricEntity)
//                .where(qSubMetricEntity.deleted.isFalse())
//                .fetch();
//
//        SubMetricEntity c = queryFactory.selectFrom(qSubMetricEntity)
//                .where(qSubMetricEntity.deleted.eq(false))
//                .fetchOne();
//
//        Sort sort;
//        if (page.getSort() == null)
//            sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"), new Sort.Order(Sort.Direction.ASC, "value"));
//        else
//            sort = new Sort(page.getSort().getField().toLowerCase().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, page.getSort().getField());
//        Pageable pageSpecification = new PageRequest(page.getPagination().getPageNumber() - 1, page.getPagination().getPageSize(), sort);
//
//        BooleanBuilder predicate = new BooleanBuilder();
//        Path<SubMetricEntity> subMetricEntityPath = Expressions.path(SubMetricEntity.class, "sub_metric");
//
//        Path<Boolean> deleted = Expressions.path(Boolean.class, subMetricEntityPath, "deleted");
//        Constant<Boolean> falseValue = (Constant<Boolean>) Expressions.constant(false);
////        Operator<Boolean> operation = new Operator<Boolean>();
//
//        if (page.getFilters() != null)
//            for (FilterDto filter : page.getFilters()) {
//                Path<String> field = Expressions.path(String.class, subMetricEntityPath, filter.getField());
//                Constant<String> value = (Constant<String>) Expressions.constant(filter.getValue());
//                if (true) predicate.or(Expressions.predicate(, field, value));
//                else predicate.and(Expressions.predicate(filter.getOps(), field, value));
//            }
//
//        predicate.and(Expressions.predicate(Ops.EQ, deleted, falseValue));
//
//        return repository.findAll(predicate, pageSpecification).getContent();

//        return  null;


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
//        List<T> lsit =  getModels(page);
        PageResult<T> pageResult = new PageResult<>();
        entityInformation = JpaEntityInformationSupport.getEntityInformation(getEntityClass(), entityManager);
        repository = new QueryDslJpaRepository<T, ID>((JpaEntityInformation<T, ID>) entityInformation, entityManager);
        Sort sort;
        if (page.getSort() == null)
            sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"), new Sort.Order(Sort.Direction.ASC, "value"));
        else
            sort = new Sort(page.getSort().getOrder().toLowerCase().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, page.getSort().getField());
        Pageable pageSpecification = new PageRequest(page.getPagination().getPageNumber() - 1, page.getPagination().getPageSize(), sort);

        BooleanBuilder predicate = new BooleanBuilder();
//        PathBuilder<T> entityPath = new PathBuilder<>(getEntityClass(), "user");
        PathBuilder<T> entityPath2 = new PathBuilder<>(getEntityClass(), "user");
        List<BooleanExpression> predicates = new ArrayList<>();


        String variable = getEntityClass().getSimpleName().substring(0, getEntityClass().getSimpleName().length() - 6);
        SimplePath<T> model = Expressions.path(getEntityClass(), "deleted");
        SimplePath<Boolean> deleted = Expressions.path(Boolean.class, model, "deleted");
        SimplePath<Long> count = Expressions.path(Long.class, model, "deleted");
        Constant<Boolean> falseValue = (Constant<Boolean>) Expressions.constant(false);

        predicate.and(Expressions.predicate(Ops.EQ, deleted,  falseValue));

        PathBuilder<T> entityPath = new PathBuilder<>(getEntityClass(), variable);
        BooleanPath asdf = entityPath.getBoolean("id");
        BooleanExpression expressionsList = filterResults(page.getFilters());

//        Page<T> firstResult = repository.findAll(predicate, pageSpecificat
//        predicate.and(Expressions.predicate(Ops.EQ, count, falseValue));


//        List<T> result = repository.findAll(predicate, pageSpecification).getContent();
//        pageResult.setData(lsit);
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
////
//        CriteriaQuery<T> queri = criteriaBuilder.createQuery(getEntityClass());
//
//        Root<T> entityRoot = queri.from(getEntityClass());
//
//        queri = addPathProperties(queri, criteriaBuilder ,entityRoot, page.getFetchFields());


        
       if (page.isEnablePaging()) {
        pageResult.setTotal( repository.findAll(expressionsList).size());
    }


        List<T> res = repository.findAll(expressionsList);
//
//        queri = resultOrder(queri, criteriaBuilder, entityRoot, page.getSort());
//
//
//        TypedQuery<T> qry = entityManager.createQuery(queri);
////        int sfa = qry.getMaxResults();
////        qry.

//        qry = addPagination(qry, page.getPagination());
//
//
//        List<T> entities = qry.getResultList();
        pageResult.setData(res);
        pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODELS);
        return pageResult;
    }
    public BooleanExpression getPredicate(FilterDto filter) {
        PathBuilder<T> entityPath = new PathBuilder<>(getEntityClass(), filter.getField());
        BooleanPath asdf = entityPath.getBoolean("id");

        if (isNumeric(filter.getField().toString())) {
            NumberPath<Float> path = entityPath.getNumber(filter.getField(), Float.class);
            float value = Float.parseFloat(filter.getValue().toString());
            switch (filter.getOperator()) {
                case EQ:
                    return path.eq(value);
                case  NE:
                    return path.ne(value);
                case GT:
                    return path.gt(value);
                case GE:
                    return path.goe(value);
                case LT:
                    return path.lt(value);
                case LE:
                    return path.loe(value);
                case IS_NULL:
                    break;

                case IS_NOT_NULL:
                    break;
                case LIKE:


                    break;
                case STARTS_WITH:
                    break;
                case ENDS_WITH:
                    break;
                case IN:

                    return path.in(value);


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

            }
        }
        else if(filter.getField().equals(BaseEntity.DELETED)){
            BooleanPath path = entityPath.getBoolean(filter.getField());
            return path.eq(Boolean.parseBoolean(filter.getValue()));

        }
        else {
            StringPath path = entityPath.getString(filter.getField());
            switch (filter.getOperator()) {
                case EQ:
                    return path.eq(filter.getValue());
                case  NE:
                    return path.ne(filter.getValue());
                case GT:
                    return path.gt(filter.getValue());
                case GE:
                    return path.goe(filter.getValue());
                case LT:
                    return path.lt(filter.getValue());
                case LE:
                    return path.loe(filter.getValue());
                case IS_NULL:
                    break;

                case IS_NOT_NULL:
                    break;
                case LIKE:
                    return path.like(StringUtil.PERCENT+filter.getValue()+StringUtil.PERCENT);
                case STARTS_WITH:
                    break;
                case ENDS_WITH:
                    break;
                case IN:

                    return path.in(filter.getValue());


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

            }



        }
        return null;
    }

    private BooleanExpression filterResults(List<FilterDto> filters) {
        List<BooleanExpression> predicates = new ArrayList<>();
//        MyUserPredicate predicate;
        for (FilterDto filter : filters) {

            BooleanExpression exp = getPredicate(filter);
            if (exp != null) {
                predicates.add(exp);
            }
        }
        BooleanExpression result2 = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result2 = result2.and(predicates.get(i));
        }
        return result2;
//        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
//        Expression<T> pathRoot;
//        Boolean dotChecher = false;
//
//        for (FilterDto filter : filters) {
//            dotChecher = false;
//            String path = filter.getField();
//            String property = BaseEntity.ID;
//            if(filter.getField().contains(StringUtil.DOT)) {
//                dotChecher = true;
//
//
//                 path = filter.getField().substring(0, filter.getField().lastIndexOf('.'));
//                 property = filter.getField().substring(filter.getField().lastIndexOf('.') + 1);
//
//            }
//
//
//
//
//
//
//            switch (filter.getOperator()) {
//                case EQ:
//
//
//                    if (filter.getField().equals(BaseEntity.DELETED))
//                        predicates.add(criteriaBuilder.equal(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()) , Boolean.parseBoolean(filter.getValue())));
//
//                    else
//                        predicates.add(criteriaBuilder.equal(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), Boolean.parseBoolean(filter.getValue())));
//                    break;
//                case NE:
//                    predicates.add(criteriaBuilder.notEqual(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), filter.getValue()));
////                    cb.notEqual(area, areaParam)
//                    break;
//                case GT:
//
//                    predicates.add(isCreatable(filter.getValue()) ?
//                            criteriaBuilder.greaterThan(  dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()),
//
//                                     Float.parseFloat(filter.getValue())) :
//                            criteriaBuilder.greaterThan(  dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()),
//
//                                    filter.getValue()));
////                    cb.greaterThan(name, nameParam)
////                    cb.gt(area, areaParam)
//                    break;
//                case GE:
////                    cb.greaterThanOrEqualTo(name, nameParam)
////                    cb.ge(area, areaParam);
//                    predicates.add(isCreatable(filter.getValue()) ?
//                            criteriaBuilder.ge(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
//                            criteriaBuilder.greaterThanOrEqualTo(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), filter.getValue()));
//                    break;
//                case LT:
//
//                    predicates.add(isCreatable(filter.getValue()) ?
//                            criteriaBuilder.lt(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
//                            criteriaBuilder.lessThan(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), filter.getValue()));
////                    cb.lt(area, areaParam)
//                    break;
//                case LE:
////                    String field = filter.getField();
////                    String value = filter.getValue();
//                    predicates.add(isCreatable(filter.getValue()) ?
//                            criteriaBuilder.le(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), Float.parseFloat(filter.getValue())) :
//                            criteriaBuilder.lessThanOrEqualTo(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()), filter.getValue()));
////                    cb.le(area, areaParam)
//                    break;
//                case IS_NULL:
////                    cb.isNull(name)
//                    break;
//
//                case IS_NOT_NULL:
////                    cb.isNotNull(name)
//                    break;
//                case LIKE:
//
////                    Predicate l1 = cb.like(path, param);
////                    Predicate l2 = cb.like(path, "a%");
//                    predicates.add(criteriaBuilder.like(dotChecher? entityRoot.join(path).get(property) : entityRoot.get(filter.getField()),
//                            StringUtil.PERCENT + filter.getValue() + StringUtil.PERCENT));
//
//
//                    break;
//                case STARTS_WITH:
//                    break;
//                case ENDS_WITH:
//                    break;
//                case IN:
//
//
//                    break;
//                case JSON_EQ:
//                    break;
//                case JSON_EXISTS:
//                    break;
//                case JSON_NE:
//                    break;
//                case JSON_NOT_EXISTS:
//                    break;
//                case AND:
////                    and();
//                    break;
//                case OR:
//                    break;
//                case NOT:
//                    break;
//                default:
//                    //code to be executed if all cases are not matched;
//            }
//
//        }
//        return queri.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
    }

//    private Expression<Object> joinOrNotJoin(Root<T> entityRoot, String field){
//        String path = field.substring(0, field.lastIndexOf('.'));
//        String property = field.substring(field.lastIndexOf('.') + 1);
//        return field.contains(StringUtil.DOT) ?
//                entityRoot.join(path).get(property) :
//                entityRoot.get(field);
//    };

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

        if(sort.getField().contains(StringUtil.DOT)){
            String path = sort.getField().substring(0, sort.getField().lastIndexOf('.'));
            String property = sort.getField().substring(sort.getField().lastIndexOf('.') + 1);

            Join<T, Object> joinedEntity = entityRoot.join(path);
            queri = sort.getOrder().toLowerCase().equals("asc") ?
                    queri.orderBy(criteriaBuilder.asc(joinedEntity.get(property))) :
                    queri.orderBy(criteriaBuilder.desc(joinedEntity.get(property)));


        }
        else{
            queri = sort.getOrder().toLowerCase().equals("asc") ?
                    queri.orderBy(criteriaBuilder.asc(entityRoot.get(sort.getField()))) :
                    queri.orderBy(criteriaBuilder.desc(entityRoot.get(sort.getField())));
        }



        return queri;
    }

    private TypedQuery<T> addPagination(TypedQuery<T> qry, PaginationDto pagination) {
//        pagination.getPageNumber();

        return qry.setFirstResult((pagination.getPageNumber() - 1) * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize());
        /*.setMaxResults((pagination.getPageNumber() * pagination.getPageSize()) - 1)*/
    }



    private CriteriaQuery<T> addPathProperties(CriteriaQuery<T> query, CriteriaBuilder criteriaBuilder, Root<T> entityRoot, List<String> fields) throws ClassNotFoundException {
        List<Selection<?>> properties = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> nonPrimitiveType = new ArrayList<>();
        properties.add(entityRoot.get(BaseEntity.DELETED));
        for(String field : fields){
            if(!field.contains("$")) {
                if(field.contains(StringUtil.DOT)){
                    String path = field.substring(0, field.lastIndexOf('.'));
                    String property = field.substring(field.lastIndexOf('.') + 1);
                    Join<T, Object> p = entityRoot.join(path);
//                        query.multiselect(entityRoot, p.get("id"));
                    properties.add(p.get(property));
                }
                else if(entityRoot.get(field).getModel()!=null && (ClassUtils.isPrimitiveOrWrapper(entityRoot.get(field).getModel().getBindableJavaType()) || entityRoot.get(field).getModel().getBindableJavaType().getSimpleName().equals("String"))){

                    properties.add(entityRoot.get(field));
                }
                else{

                }
            }

            String strf = "";
        }

//
//        return query.multiselect(list.stream()
//                .map(f -> entityRoot.get(f))
//                .collect(Collectors.toList()));
        return query.multiselect(properties);
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