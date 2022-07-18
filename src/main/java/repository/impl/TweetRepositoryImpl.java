package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Tweet;
import entity.User;
import repository.TweetRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet, Long>
        implements TweetRepository {

    public TweetRepositoryImpl(EntityManager em) {

        super(em);

    }

    @Override
    public Class<Tweet> getClassType() {

        return Tweet.class;

    }

    @Override
    public List<Tweet> findByUser(User user) {

        return em.createQuery("from Tweet t where t.sender = :sender", Tweet.class)
                .setParameter("sender", user)
                .getResultList();

    }

    @Override
    public Tweet likeTweet(Tweet tweet, User user) {

        List<User> likes = tweet.getLikes();

        likes.add(user);

        tweet.setLikes(likes);

        tweet = save(tweet);

        return tweet;

    }

    @Override
    public Tweet removeLike(Tweet tweet, User user) {

        List<User> likes = tweet.getLikes();

        likes.remove(user);

        tweet.setLikes(likes);

        tweet = save(tweet);

        return tweet;

    }

}
