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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import com.payAm.core.util.FilterOperatorUtil;
//import com.payAm.core.util.StringUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.payAm.core.constant.BaseConstants.QUERY;


public abstract class BaseDao<I extends Serializable, E extends BaseEntity> {

    @PersistenceContext
    EntityManager entityManager;


    public PageResult<E> find(PageDto page) throws Exception {
        PageResult<E> pageResult = new PageResult<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> queri = criteriaBuilder.createQuery(getEntityClass());
        Root<E> entityRoot = queri.from(getEntityClass());
        queri.select(entityRoot);

        // TODO now we can add property and where
        // Predicate p = cb.notEqual(e.type(), cb.literal(Country.class));

        TypedQuery<E> qry = entityManager.createQuery(queri);

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


    private CriteriaQuery<E> addPathProperties(CriteriaQuery<E> query, List<String> fields) {

      /*  PathProperties pathProperties = new PathProperties();
        for (String fullAssociationPath : fields) {
            if (fullAssociationPath.contains(StringUtil.DOT)) {
                String path = fullAssociationPath.substring(0, fullAssociationPath.lastIndexOf('.'));
                String property = fullAssociationPath.substring(fullAssociationPath.lastIndexOf('.') + 1);
                pathProperties.addToPath(path, property);
            }
            else pathProperties.addToPath(null, fullAssociationPath);
        }*/
        /*query.apply(pathProperties);*/
        return query;
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