package com.payAm.core.ebean;


//import com.avaje.ebean.Expr;
//import com.avaje.ebean.ExpressionList;
//import com.avaje.ebean.Junction;
//import com.avaje.ebean.Query;
//import com.avaje.ebean.text.PathProperties;
import com.payAm.core.dto.FilterDto;
import com.payAm.core.dto.PageDto;
import com.payAm.core.dto.PaginationDto;
import com.payAm.core.dto.SorterDto;
import com.payAm.core.i18n.CoreMessagesCodes;
import com.payAm.core.model.BaseEntity;
//import com.payAm.core.util.FilterOperatorUtil;
//import com.payAm.core.util.StringUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class BaseDao<I extends Serializable, E extends BaseEntity> {

    @PersistenceContext
    EntityManager entityManager;


    public PageResult<E> find(PageDto page) throws Exception {
        PageResult<E> pageResult = new PageResult<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<E> queri = criteriaBuilder.createQuery(getEntityClass());

        Root<E> entityRoot = queri.from(getEntityClass());
        queri.select(addPathProperties(entityRoot, page.getFetchFields()));

        queri = filterResults(queri,criteriaBuilder, entityRoot,  page.getFilters());

        queri = resultOrder(queri,criteriaBuilder, entityRoot,page.getSort());


//        Root<Country> c = q.from(Country.class);

//        entityRoot = addPathProperties(entityRoot, page.getFetchFields());
//        queri.select(addPathProperties(entityRoot, page.getFetchFields()));

//        queri.select(criteriaBuilder.construct(getEntityClass(),
//                entityRoot.get("name"), entityRoot.get("capital").get("name")));
        Selection<E> add = queri.getSelection();
//        queri.select(criteriaBuilder.construct(getEntityClass(),addPathProperties(entityRoot, page.getFetchFields()));


        // TODO now we can add property and where
        // Predicate p = cb.notEqual(e.type(), cb.literal(Country.class));

        TypedQuery<E> qry = entityManager.createQuery(queri);
        if (page.isEnablePaging()) {
            pageResult.setTotal(qry.getResultList().size());
        }
        qry = addPagination(qry, page.getPagination());


        List<E> entities = qry.getResultList();
        pageResult.setData(entities);
        pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODELS);
        return pageResult;







/*
        try {

            Query<E> query = addPathProperties(null, page.getFetchFields());
            ExpressionList<E> expressionList = addWhereClauses(query.where(), page.getFetchFields(), page.getFilters(), page.getAdvancedFilter());
            if (page.isEnablePaging()) {
                pageResult.setTotal(expressionList.findRowCount());
            }
            if (page.isEnableSorting()) {
                expressionList = addSortExpression(expressionList, page.getSort());
            }
            if (page.isEnablePaging())
                query = addPagingExpression(expressionList, page.getPagination());

//            pageResult.setData(entities);
//            pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODELS);
        }
        catch (Exception e) {
            throw new Exception(CoreMessagesCodes.ERROR_LOAD_MODELS);
        }
        return pageResult;*/
    }

    private CriteriaQuery<E> filterResults(CriteriaQuery<E> queri, CriteriaBuilder criteriaBuilder, Root<E> entityRoot, List<FilterDto> filters) {
        for(FilterDto filter : filters) {
            switch(filter.getOperator()){
                case EQ:
                    if(filter.getField().equals("deleted"))
                        queri.where(criteriaBuilder.equal(entityRoot.get(filter.getField()), Boolean.parseBoolean(filter.getValue())));
                    else
                        queri.where(criteriaBuilder.equal(entityRoot.get(filter.getField()), Boolean.parseBoolean(filter.getValue())));
                    break;
                case NE:
                    break;
                case GT:
                    break;
                case GE:
                    break;
                case LT:
                    break;
                case LE:
                    break;
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
                    break;
                case OR:
                    break;
                case NOT:
                    break;
                default:
                    //code to be executed if all cases are not matched;
            }

        }
        return queri;
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


    private Root<E> addPathProperties(Root<E> entityRoot, List<String> fields) {

        for (String fullAssociationPath : fields) {
           if (fullAssociationPath != null){
               entityRoot.get(fullAssociationPath);
           }
        }
      /*  PathProperties pathProperties = new PathProperties();
//
            if (fullAssociationPath.contains(StringUtil.DOT)) {
                String path = fullAssociationPath.substring(0, fullAssociationPath.lastIndexOf('.'));
                String property = fullAssociationPath.substring(fullAssociationPath.lastIndexOf('.') + 1);
                pathProperties.addToPath(path, property);
            }
            else pathProperties.addToPath(null, fullAssociationPath);
        }*/
        /*query.apply(pathProperties);*/
        return entityRoot;
    }


    /**
     * can be overriden in the sub class repositories
     * Note that in overriden method always call super.addWhereClauses() method for doing common works.
     * **/
    /*protected ExpressionList<E> addWhereClauses(ExpressionList<E> where, List<String> fields, List<FilterDto> filters, Boolean advancedFilter) {

        Junction<E> junction;

        if (advancedFilter) {
            junction = where.conjunction();
            for (FilterDto filter : filters) {
                junction = FilterOperatorUtil.addRestriction(junction, filter);
            }
        } else {
            junction = where.disjunction();
            FilterDto filter = filters.stream().filter(fltr -> QUERY.equals(fltr.getField())).findFirst().get();
            for (String field : fields) {
                junction.add(Expr.ilike(field, StringUtil.PERCENT + filter.getValue() + StringUtil.PERCENT));
            }
        }

        return junction.endJunction();
    }*/

  /*  private ExpressionList<E> addSortExpression(ExpressionList<E> expressionList, SorterDto sorter) {
        expressionList.order(sorter.getField() + " " + sorter.getOrder());
        return expressionList;
    }

    private Query<E> addPagingExpression(ExpressionList<E> expressionList, PaginationDto pagination) {
        return expressionList
                .setFirstRow((pagination.getPageNumber() - 1) * pagination.getPageSize())
                .setMaxRows((pagination.getPageNumber() * pagination.getPageSize()) - 1);
    }*/


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