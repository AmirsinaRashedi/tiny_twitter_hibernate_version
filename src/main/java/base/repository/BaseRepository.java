package base.repository;

import base.domain.BaseEntity;

import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T t);

    void delete(T t);

    T findById(ID id);

    List<T> findAll();

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    EntityTransaction getTransaction();


}
