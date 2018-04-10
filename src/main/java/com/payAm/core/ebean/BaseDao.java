package com.payAm.core.ebean;//package com.payAm.core.ebean;
//
////import com.avaje.ebean.Expr;
////import com.avaje.ebean.ExpressionList;
////import com.avaje.ebean.Junction;
////import com.avaje.ebean.Model.Find;
////import com.avaje.ebean.Query;
////import com.avaje.ebean.text.PathProperties;
//import com.payAm.core.dto.FilterDto;
//import com.payAm.core.dto.PageDto;
//import com.payAm.core.dto.PaginationDto;
//import com.payAm.core.dto.SorterDto;
//import com.payAm.core.ebean.exception.DependencyException;
//import com.payAm.core.i18n.CoreMessagesCodes;
//import com.payAm.core.model.BaseEntity;
//import com.payAm.core.util.FilterOperatorUtil;
//import com.payAm.core.util.StringUtil;
//import org.apache.commons.lang3.exception.ExceptionUtils;
////import play.i18n.Messages;
////import play.i18n.MessagesApi;
////import play.mvc.Http;
//
//
//import javax.inject.Inject;
//import javax.persistence.*;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//
//import static com.payAm.core.constant.BaseConstants.QUERY;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2016/Dec/30 - 02:20 PM
// */
//public abstract class BaseDao<I extends Serializable, E extends BaseEntity> extends BaseService /*extends Find<I, E>*/ {
//
////    @Inject MessagesApi messagesApi;
////    private Messages messages;
//
//    private Class<E> getEntityClass() {
//        Class<?> clazz = getClass();
//        while (clazz.getGenericSuperclass() != null) {
//            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
//                return (Class<E>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[1];
//            } else {
//                clazz = clazz.getSuperclass();
//            }
//        }
//        return null;
//    }
//
//    public E insert(E entity) {
//        return internalInsert(entity);
//    }
//
//    public E update(E entity) {
//        return internalUpdate(entity);
//    }
//
//    private E internalInsert(E entity) throws EntityExistsException, RollbackException {
//        try {
//            entity.insert();
//        }
//        catch (Exception e) {
//            String cause = ExceptionUtils.getRootCauseMessage(e.getCause());
//            if (!cause.isEmpty() && cause.contains("violate"))
//                throw new EntityExistsException(CoreMessagesCodes.ERROR_CONSTRAINT_VIOLATION);
//            throw new RollbackException(CoreMessagesCodes.ERROR_SAVE_MODEL);
//        }
//        return entity;
//    }
//
//    private E internalUpdate(E entity)
//            throws EntityNotFoundException, EntityExistsException, OptimisticLockException, RollbackException {
//        try {
//            try {
//                entity = retrieve(entity);
//                entity.update();
//            }
//            catch (OptimisticLockException e) {
//                throw new OptimisticLockException(CoreMessagesCodes.ERROR_OPTIMISTIC_LOCK);
//            }
//        }
//        catch (Exception e) {
//            String cause = ExceptionUtils.getRootCauseMessage(e.getCause());
//            if (!cause.isEmpty() && cause.contains("violate"))
//                throw new EntityExistsException(CoreMessagesCodes.ERROR_CONSTRAINT_VIOLATION);
//            throw new RollbackException(CoreMessagesCodes.ERROR_SAVE_MODEL);
//        }
//        return entity;
//    }
//
//    public E load(final I id) throws EntityNotFoundException {
//        E entity = (E) super.byId(id);
//        if (entity == null) {
//            throw new EntityNotFoundException(CoreMessagesCodes.ERROR_ENTITY_NOT_FOUND);
//        }
//        return entity;
//    }
//
//    public E load(final I id, List<String> fields) throws Exception {
//        Query<E> query = addPathProperties(super.query(), fields);
//        try {
//            E entity = query.setId(id).findUnique();
//            if (entity == null)
//                throw new EntityNotFoundException(CoreMessagesCodes.ERROR_ENTITY_NOT_FOUND);
//            return entity;
//        }
//        catch (EntityNotFoundException e) {
//            throw new EntityNotFoundException(e.getMessage());
//        }
//        catch (NonUniqueResultException e) {
//            throw new NonUniqueResultException(CoreMessagesCodes.ERROR_NON_UNIQUE_RESULT_FOUND);
//        }
//        catch (Exception e) {
//            throw new Exception(CoreMessagesCodes.ERROR_LOAD_MODEL);
//        }
//    }
//
//    private void delete(E entity) throws DependencyException, OptimisticLockException {
//        if (hasAnyDependency(entity))
//            throw new DependencyException(CoreMessagesCodes.ERROR_ENTITY_HAS_DEPENDENCY);
//        if (canBePhysicallyDeleted())
//            super.deleteById((I) entity.id);
//        else {
//            entity.logicallyDelete();
//            internalUpdate(entity);
//        }
//    }
//
//    public E delete(I id) throws EntityNotFoundException, NonUniqueResultException, DependencyException {
//        E entity = load(id);
//        delete(entity);
//        return entity;
//    }
//
//    /* This method can be overriden in the sub class dao */
//    protected Boolean hasAnyDependency(E entity) {
//        return false;
//    }
//
//    /* This method can be overriden in the sub class dao */
//    protected Boolean canBePhysicallyDeleted() {
//        return false;
//    }
//
//    private E retrieve(E entity) throws EntityNotFoundException, DependencyException {
//        if (!entity.deleted) {
//            E oldEntity = load((I) entity.id);
//            if (oldEntity.deleted) {
//                if (canBeRetrieved()) {
//                    entity.logicallyRetrieve();
//                } else
//                    throw new DependencyException(CoreMessagesCodes.ERROR_ENTITY_CANT_BE_RETRIEVED);
//            }
//        }
//        return entity;
//    }
//
//    /* This method can be overriden in the sub class dao */
//    protected Boolean canBeRetrieved() {
//        return true;
//    }
//
//    public PageResult<E> find(PageDto page) throws Exception {
//        PageResult<E> pageResult = new PageResult<>();
//        try {
//            Query<E> query = addPathProperties(super.query(), page.getFetchFields());
//            ExpressionList<E> expressionList = addWhereClauses(query.where(), page.getFetchFields(), page.getFilters(), page.getAdvancedFilter());
//            if (page.isEnablePaging()) {
//                pageResult.setTotal(expressionList.findRowCount());
//            }
//            if (page.isEnableSorting()) {
//                expressionList = addSortExpression(expressionList, page.getSort());
//            }
//            if (page.isEnablePaging())
//                query = addPagingExpression(expressionList, page.getPagination());
//
//            pageResult.setData(query.findList());
//            pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODELS);
//        }
//        catch (Exception e) {
//            throw new Exception(CoreMessagesCodes.ERROR_LOAD_MODELS);
//        }
//        return pageResult;
//    }
//
//    private Query<E> addPathProperties(Query<E> query, List<String> fields) {
//
//        PathProperties pathProperties = new PathProperties();
//        for (String fullAssociationPath : fields) {
//            if (fullAssociationPath.contains(StringUtil.DOT)) {
//                String path = fullAssociationPath.substring(0, fullAssociationPath.lastIndexOf('.'));
//                String property = fullAssociationPath.substring(fullAssociationPath.lastIndexOf('.') + 1);
//                pathProperties.addToPath(path, property);
//            }
//            else pathProperties.addToPath(null, fullAssociationPath);
//        }
//        query.apply(pathProperties);
//        return query;
//    }
//
//    private Query<E> addSelectedFields(Query<E> query, List<String> fields) {
//
//        List<String> selectedFields = StringUtil.filterStringListByNotString(fields, StringUtil.DOT, StringUtil.StringOperator.CONTAINS);
//        if (fields.size() != selectedFields.size())
//            throw new IllegalArgumentException(CoreMessagesCodes.ERROR_ILLEGAL_SELECT_FIELD);
//        query = query.select(StringUtil.convertStringArrayToString(selectedFields.toArray(new String[selectedFields.size()])));
//        return query;
//    }
//
//    /**
//     * can be overriden in the sub class dao
//     * Note that in overriden method always call super.addWhereClauses() method for doing common works.
//     * **/
//    protected ExpressionList<E> addWhereClauses(ExpressionList<E> where, List<String> fields, List<FilterDto> filters, Boolean advancedFilter) {
//
//        Junction<E> junction;
//
//        if (advancedFilter) {
//            junction = where.conjunction();
//            for (FilterDto filter : filters) {
//                junction = FilterOperatorUtil.addRestriction(junction, filter);
//            }
//        } else {
//            junction = where.disjunction();
//            FilterDto filter = filters.stream().filter(fltr -> QUERY.equals(fltr.getField())).findFirst().get();
//            for (String field : fields) {
//                junction.add(Expr.ilike(field, StringUtil.PERCENT + filter.getValue() + StringUtil.PERCENT));
//            }
//        }
//
//        return junction.endJunction();
//    }
//
//    private ExpressionList<E> addSortExpression(ExpressionList<E> expressionList, SorterDto sorter) {
//        expressionList.order(sorter.getField() + " " + sorter.getOrder());
//        return expressionList;
//    }
//
//    private Query<E> addPagingExpression(ExpressionList<E> expressionList, PaginationDto pagination) {
//        return expressionList
//                .setFirstRow((pagination.getPageNumber() - 1) * pagination.getPageSize())
//                .setMaxRows((pagination.getPageNumber() * pagination.getPageSize()) - 1);
//    }
//
//    public E loadByUniqueProperty(String propertyName, Object propertyValue) {
//        try {
//            return super.query()
//                    .where()
//                    .eq(propertyName, propertyValue)
//                    .findUnique();
//        }
//        catch (NonUniqueResultException e) {
//            throw new NonUniqueResultException(CoreMessagesCodes.ERROR_NON_UNIQUE_RESULT_FOUND);
//        }
//    }
//
////    protected Messages getMessages() {
////        if (this.messages == null) {
////            this.messages = messagesApi.preferred(Http.Context.current().request());
////        }
////        return this.messages;
////    }
//}
