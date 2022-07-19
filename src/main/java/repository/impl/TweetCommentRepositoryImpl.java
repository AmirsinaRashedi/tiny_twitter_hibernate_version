package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Tweet;
import entity.TweetComment;
import entity.User;
import repository.TweetCommentRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TweetCommentRepositoryImpl extends BaseRepositoryImpl<TweetComment, Long>
        implements TweetCommentRepository {

    public TweetCommentRepositoryImpl(EntityManager em) {

        super(em);

    }

    @Override
    public Class<TweetComment> getClassType() {

        return TweetComment.class;

    }

    @Override
    public List<TweetComment> findByTweet(Tweet tweet) {

        return em.createQuery("from TweetComment tc where tc.tweet = :tweet", TweetComment.class)
                .setParameter("tweet", tweet)
                .getResultList();

    }

    @Override
    public List<TweetComment> findByUser(User user) {

        return em.createQuery("from TweetComment tc where tc.sender = :user", TweetComment.class)
                .setParameter("user", user)
                .getResultList();

    }


}
