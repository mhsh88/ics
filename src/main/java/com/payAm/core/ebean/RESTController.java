package com.payAm.core.ebean;


import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;















//package com.payAm.core.ebean;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.payAm.core.dto.BaseDto;
//import com.payAm.core.dto.PageDto;
//import com.payAm.core.i18n.CoreMessagesCodes;
//import com.payAm.core.model.BaseEntity;
//import com.payAm.core.util.ReflectionUtil;
//import com.payAm.core.util.StringUtil;
//import com.payAm.core.view.BaseView;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.*;
//import play.libs.Json;
//import play.mvc.BodyParser;
//import play.mvc.Result;
//
//import java.io.Serializable;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Jan/10 - 01:44 PM
// */
//
//
//@RequestMapping("/u")
//public abstract class RestController<M extends BaseEntity, I extends Serializable, V extends BaseView> extends BaseController {
//
//    public abstract BaseDao<I, M> getDao();
//
//    @Autowired
//    BaseService baseDAO;
//
//    //@SubjectPresent
//    public Result load(I id) {
//
//        PageResult<M> pageResult = new PageResult<>();
//
//        try {
//            M model = getDao().load(id, getFetchedFields());
//            afterLoad(model);
//            pageResult.addData(model);
//            pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODEL);
//            return ok(writeJson(pageResult));
//        }
//        catch (Exception e) {
//            pageResult.unsuccessfulOperation(e.getMessage());
//            return badRequest(Json.toJson(pageResult));
//        }
//    }
//
//    protected void afterLoad(M model) { }
//
//    /* This method can be overriden in the subclasses */
//    protected List<String> getFetchedFields() {
//        return ReflectionUtil.getAllEntityColumnNames(getModelClass());
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected List<String> getLoadConverterFields() {
//        List<String> conversionPolicy = new ArrayList<>();
//        for (Method method : getModelClass().getMethods()) {
//            if (ReflectionUtil.isPrimitiveOrBasicPropertyGetterMethod(method)) {
//                String propertyName = StringUtil.toFirstLower(method.getName().substring(3));
//                conversionPolicy.add(propertyName);
//            }
//        }
//        return conversionPolicy;
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected List<String> getSaveOrUpdateConverterFields() {
//        List<String> conversionPolicy = new ArrayList<>();
//        for (Method method : getModelClass().getMethods()) {
//            if (ReflectionUtil.isPrimitiveOrBasicPropertyGetterMethod(method)) {
//                String propertyName = StringUtil.toFirstLower(method.getName().substring(3));
//                conversionPolicy.add(propertyName);
//            }
//        }
//        return conversionPolicy;
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected Map<Class<? extends BaseDto>, Class<? extends BaseEntity>> getConverterSpecialClassesMap() { return null; }
//
//    @BodyParser.Of(BodyParser.Json.class)
//    protected Result insert() {
//
//        PageResult<M> pageResult = new PageResult<>();
//
//        try {
//            M model = readJson(request().body().asJson());
//            model = beforeInsert(model);
//            M newModel = getDao().insert(model);
//            newModel = afterInsert(newModel);
//            pageResult.addData(newModel);
//            pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_SAVE_MODEL);
//            return ok(writeJson(pageResult));
//        }
//        catch (Exception e) {
//            pageResult.unsuccessfulOperation(e.getMessage());
//            return badRequest(Json.toJson(pageResult));
//        }
//    }
//
//    @BodyParser.Of(BodyParser.Json.class)
//    protected Result update() {
//
//        PageResult<M> pageResult = new PageResult<>();
//
//        try {
//            M model = readJson(request().body().asJson());
//            M oldModel = getDao().load((I) model.id);
//            model = beforeUpdate(oldModel, model);
//            M newModel = getDao().update(model);
//            newModel = afterUpdate(newModel);
//            pageResult.addData(newModel);
//            pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_SAVE_MODEL);
//            return ok(writeJson(pageResult));
//        }
//        catch (Exception e) {
//            pageResult.unsuccessfulOperation(e.getMessage());
//            return badRequest(Json.toJson(pageResult));
//        }
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected M beforeInsert(M newEntity) throws Exception {
//        return newEntity;
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected M afterInsert(M newEntity) {
//        return newEntity;
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected M beforeUpdate(M oldEntity, M newEntity) throws Exception {
//        return newEntity;
//    }
//
//    /* This method can be overriden in the subclasses */
//    protected M afterUpdate(M newEntity) {
//        return newEntity;
//    }
//
//    protected Result delete(I id) {
//        PageResult<M> pageResult = new PageResult<>();
//        try {
//            getDao().delete(id);
//            pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_DELETE_MODEL);
//            return ok(writeJson(pageResult));
//        }
//        catch (Exception e) {
//            pageResult.unsuccessfulOperation(e.getMessage());
//            return badRequest(Json.toJson(pageResult));
//        }
//    }
//
//    public Result loadModels() {
//
//        JsonNode pageNode = Json.parse(request().queryString().size() > 0 ?
//                request().queryString().keySet().toArray()[0].toString() : "{}");
//        PageDto page = Json.fromJson(pageNode, PageDto.class);
//        PageResult<M> modelsPageResult = new PageResult<>();
//
//        try {
//            List<String> fields = getFetchedFields();
//            page.setFetchFields(fields);
//            modelsPageResult = find(page);
//            for (M model : modelsPageResult.getData()) {
//                afterLoad(model);
//            }
//            return ok(writeJson(modelsPageResult));
//        }
//        catch (Exception e) {
//            modelsPageResult.unsuccessfulOperation(e.getMessage());
//            return badRequest(Json.toJson(modelsPageResult));
//        }
//    }
//
//    protected PageResult<M> find(PageDto page) throws Exception {
//        return getDao().find(page);
//    }
//
//    @BodyParser.Of(BodyParser.Json.class)
//    public Result loadOptions() {
//
//        PageDto page = request().body().as(PageDto.class);
//        HashMap<Long, String> options = new HashMap<>();
//
//        try {
//
//            /*if (page.getSorter() != null)
//                page.getSorter().setAscending(true);*/
//
//            PageResult<M> pageResult = find(page);
//            if (pageResult != null) {
//                for (M model : pageResult.getData()) {
//                    try {
//                        Object optionTitle = ReflectionUtil.invokeGetter(model, page.getOptionField());
//                        options.put(model.id, optionTitle.toString());
//                    } catch (Exception e) {
//
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            PageResult<M> pageResult = new PageResult<>();
//            pageResult.unsuccessfulOperation(e.getMessage());
//            return badRequest(Json.toJson(pageResult));
//        }
//        return ok(Json.toJson(options));
//    }
//
//    protected Class<M> getModelClass() {
//        Class<?> clazz = getClass();
//        while (clazz.getGenericSuperclass() != null) {
//            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
//                return (Class<M>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
//            } else {
//                clazz = clazz.getSuperclass();
//            }
//        }
//        return null;
//    }
//
//    protected Class<V> getViewClass() {
//        Class<?> clazz = getClass();
//        while (clazz.getGenericSuperclass() != null) {
//            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
//                return (Class<V>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[2];
//            } else {
//                clazz = clazz.getSuperclass();
//            }
//        }
//        return null;
//    }
//
//    protected String writeJson(PageResult<M> pageResult) throws JsonProcessingException {
//        return Json.mapper().writerWithView(getViewClass()).writeValueAsString(pageResult);
//    }
//
//    protected M readJson(JsonNode jsonNode) throws java.io.IOException {
//        return Json.mapper().readerWithView(getViewClass()).forType(getModelClass()).readValue(jsonNode);
//    }
//
//}
