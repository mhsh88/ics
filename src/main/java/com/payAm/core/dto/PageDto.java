package com.payAm.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.payAm.core.constant.PagingPropertiesConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.payAm.core.constant.BaseConstants.QUERY;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/05 - 08:36 AM
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PageDto implements Serializable, PagingPropertiesConstants {

    private List<FilterDto> filters = new ArrayList<>();
    private SorterDto sort = new SorterDto();
    private List<String> fetchFields = new ArrayList<>();
    private String optionField;
    private Boolean enablePaging = false;
    private PaginationDto pagination;

    public Boolean getAdvancedFilter() {
        return filters.stream().noneMatch(filter -> QUERY.equals(filter.getField()));
    }

    public List<FilterDto> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDto> filters) {
        this.filters = filters;
    }

    public Boolean isEnablePaging() {
        return pagination != null;
    }

    public Boolean isEnableSorting() {
        return sort.getField() != null && !Objects.equals(sort.getField(), "")
                && sort.getOrder() != null && !Objects.equals(sort.getOrder(), "");
    }

    public void setEnablePaging(Boolean enablePaging) {
        this.enablePaging = enablePaging;
    }

    public List<String> getFetchFields() {
        return fetchFields;
    }

    public void setFetchFields(List<String> fetchFields) {
        this.fetchFields = fetchFields;
    }

    public void addFetchField(String field) {
        if (!getFetchFields().contains(field))
            getFetchFields().add(field);
    }

    public void removeFetchField(String field) {
        getFetchFields().remove(field);
    }

    public String getOptionField() {
        return optionField;
    }

    public void setOptionField(String optionField) {
        this.optionField = optionField;
    }

    public SorterDto getSort() {
        return sort;
    }

    public void setSort(SorterDto sort) {
        this.sort = sort;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        /*if (pagination != null && pagination.getPageSize() <= 0) {
            setEnablePaging(false);
        }*/
        this.pagination = pagination;
    }
}
