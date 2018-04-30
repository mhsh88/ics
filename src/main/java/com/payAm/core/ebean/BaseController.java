package com.payAm.core.ebean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.payAm.core.dto.PageDto;
import com.payAm.core.i18n.CoreMessagesCodes;
import com.payAm.core.model.BaseEntity;
import com.payAm.core.util.ReflectionUtil;
import com.payAm.core.view.BaseView;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scala.util.parsing.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;


public abstract class BaseController<T extends BaseEntity, ID extends Serializable, V extends BaseView> {

    private Logger logger = LoggerFactory.getLogger(RestController.class);

    ObjectMapper mapper = new ObjectMapper();

    protected CrudRepository<T, ID> repo;


    public abstract BaseDao< T, ID> getDao();


    public BaseController(CrudRepository<T, ID> repo) {
        this.repo = repo;
    }
    protected void afterLoad(T model) { }

    @RequestMapping/*(value = "/{queryString}", method=RequestMethod.GET)*/
    public @ResponseBody
    ResponseEntity<String> listAll(@RequestParam Map<String,String> params, HttpServletRequest request
                                                 ) throws IOException {
        String queryString = request.getQueryString();
        PageDto pageDto = getPageDtoFromJson(params);

        PageResult<T> modelsPageResult = new PageResult<>();

        try {
            List<String> fields = getFetchedFields();
            pageDto.setFetchFields(fields);
            modelsPageResult = find(pageDto);
            for (T model : modelsPageResult.getData()) {
                afterLoad(model);
            }

            return ResponseEntity.ok().body(mapper.writerWithView(getViewClass()).writeValueAsString(modelsPageResult));
//            return ResponseEntity.ok().body(modelsPageResult);
        }
        catch (Exception e) {
            e.printStackTrace();
            modelsPageResult.unsuccessfulOperation(e.getMessage());
            return ResponseEntity.badRequest().body(mapper.writerWithView(getViewClass()).writeValueAsString(modelsPageResult));
        }

    }

    private PageDto getPageDtoFromJson(Map<String, String> params) throws IOException {
        String jsonString = "{}";

        try{
            if(params.get(params.keySet().stream().findFirst().get()).equals(""))
                jsonString = params.keySet().stream().findFirst().get().length() > 0 ?
                    params.keySet().stream().findFirst().get() : "{}";
            else
                jsonString = mapper.writeValueAsString(params);

        }
        catch (Exception e){

        }

//        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, PageDto.class);

    }

    @RequestMapping(method=RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String, Object> create(@RequestBody T json) {
        logger.debug("create() with body {} of type {}", json, json.getClass());

        T created = this.repo.save(json);

        Map<String, Object> m = Maps.newHashMap();
        m.put("success", true);
        m.put("created", created);
        return m;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> get(@PathVariable ID id) throws JsonProcessingException {
        PageResult<T> pageResult = new PageResult<>();

        T model =  this.repo.findOne(id);

        pageResult.addData(model);
        pageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODEL);

        return  ResponseEntity.ok().body(mapper.writerWithView(getViewClass()).writeValueAsString(pageResult));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST/*, consumes={MediaType.APPLICATION_JSON_VALUE}*/)
    public @ResponseBody Map<String, Object> update(@PathVariable ID id, @RequestBody T json) {
        logger.debug("update() of id#{} with body {}", id, json);
        logger.debug("T json is of type {}", json.getClass());



        T entity = this.repo.findOne(id);



        try {
            BeanUtils.copyProperties(entity, json);
        }
        catch (Exception e) {
            logger.warn("while copying properties", e);
            throw Throwables.propagate(e);
        }

        logger.debug("merged entity: {}", entity);

        T updated = this.repo.save(entity);
        logger.debug("updated enitity: {}", updated);

        Map<String, Object> m = Maps.newHashMap();
        m.put("success", true);
        m.put("id", id);
        m.put("updated", updated);
        return m;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public @ResponseBody Map<String, Object> delete(@PathVariable ID id) {
        this.repo.delete(id);
        Map<String, Object> m = Maps.newHashMap();
        m.put("success", true);
        return m;
    }

    protected List<String> getFetchedFields() {
        return ReflectionUtil.getAllEntityColumnNames(getModelClass());
    }
    protected Class<T> getModelClass() {
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

    protected Class<V> getViewClass() {
        Class<?> clazz = getClass();
        while (clazz.getGenericSuperclass() != null) {
            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                return (Class<V>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[2];
            } else {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }


    protected PageResult<T> find(PageDto page) throws Exception {
        return getDao().findData(page);
    }
}