package base.service;

import base.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T t);

    void delete(T t);

    T findById(ID id);

    List<T> findAll();


}
