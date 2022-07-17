package base.service.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends BaseRepository<T, ID>>
        implements BaseService<T, ID, R> {

    protected final R repository;

    public BaseServiceImpl(R repository) {

        this.repository = repository;

    }

    @Override
    public T save(T t) {

        try {

            repository.beginTransaction();

            t = repository.save(t);

            repository.commitTransaction();

        } catch (Exception e) {

            repository.rollbackTransaction();

            e.printStackTrace();

        }

        return t;
    }

    @Override
    public void delete(T t) {

        try {

            repository.beginTransaction();

            repository.delete(t);

            repository.commitTransaction();

        } catch (Exception e) {

            repository.rollbackTransaction();

            e.printStackTrace();

        }

    }

    @Override
    public T findById(ID id) {

        T t;

        try {

            t = repository.findById(id);

        } catch (Exception e) {

            return null;

        }

        return t;

    }

    @Override
    public List<T> findAll() {

        List<T> list;

        try {

            list = repository.findAll();

        } catch (Exception e) {

            return null;

        }

        return list;
    }
}
