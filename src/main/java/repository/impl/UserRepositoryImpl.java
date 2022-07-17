package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.User;
import repository.UserRepository;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long>
        implements UserRepository {

    public UserRepositoryImpl(EntityManager em) {

        super(em);

    }

    @Override
    public User findByUsername(String username) {

        User foundUser;

        foundUser = em.createQuery("from User u where u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();

        return foundUser;

    }


    @Override
    public Class<User> getClassType() {

        return User.class;

    }
}
