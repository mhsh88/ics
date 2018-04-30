package com.payAm.core.ebean;


import com.payAm.core.model.BaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Primary
@Repository
@Transactional
public abstract class IDao<T extends BaseEntity, ID extends Serializable> implements CrudRepository<T, ID> {
}
