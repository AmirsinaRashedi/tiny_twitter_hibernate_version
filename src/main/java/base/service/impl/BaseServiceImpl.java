package base.service.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseService<T, ID> {

    protected final BaseRepository<T, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<T, ID> baseRepository) {

        this.baseRepository = baseRepository;

    }

    @Override
    public T save(T t) {

        try {

            baseRepository.beginTransaction();

            t = baseRepository.save(t);

            baseRepository.commitTransaction();

        } catch (Exception e) {

            baseRepository.rollbackTransaction();

            e.printStackTrace();

        }

        return t;
    }

    @Override
    public void delete(T t) {

        try {

            baseRepository.beginTransaction();

            baseRepository.delete(t);

            baseRepository.commitTransaction();

        } catch (Exception e) {

            baseRepository.rollbackTransaction();

            e.printStackTrace();

        }

    }

    @Override
    public T findById(ID id) {

        T t;

        try {

            t = baseRepository.findById(id);

        } catch (Exception e) {

            return null;

        }

        return t;

    }

    @Override
    public List<T> findAll() {

        List<T> list;

        try {

            list = baseRepository.findAll();

        } catch (Exception e) {

            return null;

        }

        return list;
    }
}
