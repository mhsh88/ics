package com.payAm.core.ebean;

import com.payAm.core.dto.PageDto;
import com.payAm.core.model.BaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public interface BaseDAORepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<BaseEntity, Serializable> {


}