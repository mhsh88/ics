package com.payAm.core.ebean;

import com.payAm.core.model.BaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
@Transactional
public interface BaseDAORepository extends JpaRepository<BaseEntity, Serializable> {

}
