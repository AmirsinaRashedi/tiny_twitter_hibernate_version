package util;

import repository.impl.TweetRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TweetService;
import service.UserService;
import service.impl.TweetServiceImpl;
import service.impl.UserServiceImpl;

public class ApplicationContext {

    private static UserService userService;

    private static TweetService tweetService;

    private ApplicationContext() {
    }

    public static UserService getUserService() {

        if (userService == null)
            userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtil.getEntityManager()));

        return userService;

    }

    public static TweetService getTweetService() {

        if (tweetService == null)
            tweetService = new TweetServiceImpl(new TweetRepositoryImpl(HibernateUtil.getEntityManager()));

        return tweetService;

    }

}
