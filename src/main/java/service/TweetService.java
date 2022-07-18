package service;

import base.service.BaseService;
import entity.Tweet;
import entity.User;
import repository.TweetRepository;

import java.util.List;

public interface TweetService extends BaseService<Tweet, Long, TweetRepository> {

    List<Tweet> getByUser(User user);

    Tweet createTweet(User user);

    Tweet likeTweet(User user);

    Tweet removeLike(User user);


}
