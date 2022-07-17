package base.repository.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<T, ID> {

    protected final EntityManager em;

    public BaseRepositoryImpl(EntityManager em) {

        this.em = em;

    }

    @Override
    public T save(T t) {

        if (t.getId() == null)
            em.persist(t);
        else
            t = em.merge(t);

        return t;
    }

    @Override
    public void delete(T t) {

        t = em.find(getClassType(), t.getId());

        em.remove(t);

    }

    @Override
    public T findById(ID id) {

        T t = em.find(getClassType(), id);

        return t;
    }

    @Override
    public List<T> findAll() {

        return em.createQuery("from" + getClassType().getSimpleName(), getClassType()).getResultList();

    }

    @Override
    public void beginTransaction() {

        if (!getTransaction().isActive())
            getTransaction().begin();

    }

    @Override
    public void commitTransaction() {

        if (getTransaction().isActive())
            getTransaction().commit();

    }

    @Override
    public void rollbackTransaction() {

        if (getTransaction().isActive())
            getTransaction().rollback();

    }

    @Override
    public EntityTransaction getTransaction() {

        return em.getTransaction();

    }

    public abstract Class<T> getClassType();
}
