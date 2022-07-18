package repository;

import base.repository.BaseRepository;
import entity.Tweet;
import entity.User;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet, Long> {

    List<Tweet> findByUser(User user);

    Tweet likeTweet(Tweet tweet, User user);

    Tweet removeLike(Tweet tweet, User user);

}
