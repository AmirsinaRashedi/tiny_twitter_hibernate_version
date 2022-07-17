package util;

import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

public class ApplicationContext {

    private static UserService userService;

    private ApplicationContext() {
    }

    public static UserService getUserService() {

        if (userService == null)
            userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityManager()));

        return userService;

    }

}
