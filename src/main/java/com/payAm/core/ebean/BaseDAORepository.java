package com.payAm.core.ebean;

import com.payAm.core.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseDAORepository extends JpaRepository<BaseEntity, Serializable> {
}
