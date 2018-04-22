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


public abstract class BaseController<T extends BaseEntity, ID extends Serializable, V extends BaseView> {

    private Logger logger = LoggerFactory.getLogger(RestController.class);

    ObjectMapper mapper = new ObjectMapper();

    private CrudRepository<T, ID> repo;

    @PersistenceContext
    EntityManager entityManager;

    public abstract BaseDao< T, ID> getDao();


    public BaseController(CrudRepository<T, ID> repo) {
        this.repo = repo;
    }
    protected void afterLoad(T model) { }

    @RequestMapping/*(value = "/{queryString}", method=RequestMethod.GET)*/
    public @ResponseBody
    ResponseEntity<String> listAll(@RequestParam Map<String,String> params
                                                 ) throws IOException {
        PageDto pageDto = getPageDtoFromJson(params);

/*
        @RequestParam(required = false) String queryString,
        @RequestParam MultiValueMap<T, T> requestParams,
        @RequestParam(required = false) PageDto page,
        @ModelAttribute PageDto page2,*/

        /*@RequestBody(required = false) PageDto page3,
        PageDto page4,
        Model model,
        HttpServletRequest req,
        String query*/
//        @ResponseBody
//        String query = uriInfo.getRequestUri().getQuery();
//        model.addAttribute("accept", "text/plain");
//        JsonNode pageNode = request.body().asJson();
//
//        JsonNode pageNode = Json.parse(request.getQueryString().length() > 0 ?
//                request.getQueryString().toCharArray()[0] : "{}");
//                request().queryString().keySet().toArray()[0].toString() : "{}");
//        PageDto page = Json.fromJson(pageNode, PageDto.class);

//        System.out.println(request.getQueryString());
//        request.getQueryString().length() > 0
//        String jsonString = req.getQueryString();

//        set.
//        set.stream().filter(sample::equals).findAny().orElse(null);
//        String str = set.stream().findFirst().get();
//        String jsonString = params.get(0);
//        params.
//        params.keySet().
//        JsonFactory fc = new JsonFactory();


//
//
//
//        JsonNode actualObj = null;
//
//        JsonFactory factory = mapper.getFactory();
//        JsonParser jp = factory.createJsonParser(jsonString);
//        JsonNode jsonNode = jp.readValueAsTree();
//        PageDto pageFromJsonNode = mapper.treeToValue(jsonNode, PageDto.class);
//        try {
//            actualObj = mapper.readTree(jsonString);
////            System.out.println(actualObj);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        JsonParser parser = null;
//        try {
//            parser = factory.createParser(jsonString);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        JsonNode actualObj = null;
//        try {
//            actualObj = mapper.readTree(parser);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        PageDto probe = mapper.convertValue(params, PageDto.class);
////        JsonNode jsonNode0 = mapper.convertValue(jsonString, JsonNode.class);
////        JsonNode jsonNode1 = mapper.convertValue(requestParams, JsonNode.class);
////        JsonNode jsonNode2 = mapper.valueToTree(requestParams);
////        try {
////            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode1);
////        } catch (JsonProcessingException e) {
////            e.printStackTrace();
////        }
//        PageDto page5;
//        try {
//             page5 = mapper.treeToValue(actualObj, PageDto.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        PageDto page6 = mapper.convertValue(actualObj, PageDto.class);

        PageResult<T> modelsPageResult = new PageResult<>();

        try {
            List<String> fields = getFetchedFields();
            pageDto.setFetchFields(fields);
//            Iterable<T> all1 = getdata(pageDto);
//            modelsPageResult.setData((List<T>) all1);
//            Iterable<T> all = this.repo.findAll();
//            for(T a : all1){
//                modelsPageResult.addData(a);
//            }
            modelsPageResult = find(pageDto);
            for (T model : modelsPageResult.getData()) {
                afterLoad(model);
            }

            return ResponseEntity.ok().body(mapper.writerWithView(getViewClass()).writeValueAsString(modelsPageResult));
//            return ResponseEntity.ok().body(modelsPageResult);
        }
        catch (Exception e) {
            modelsPageResult.unsuccessfulOperation(e.getMessage());
            return ResponseEntity.badRequest().body(mapper.writerWithView(getViewClass()).writeValueAsString(modelsPageResult));
//            return ResponseEntity.badRequest().body(modelsPageResult);
        }




//        modelsPageResult.setMessage(CoreMessagesCodes.SUCCESSFUL_LOAD_MODEL);


//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");



//        return (ResponseEntity<T>) ResponseEntity.ok().body(writeJson(modelsPageResult));
//        return Lists.newArrayList(all);
    }

    private PageDto getPageDtoFromJson(Map<String, String> params) throws IOException {
        String jsonString = "{}";

        try{
            jsonString = params.keySet().stream().findFirst().get().length() > 0 ?
                    params.keySet().stream().findFirst().get() : "{}";
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