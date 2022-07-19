package repository;

import base.repository.BaseRepository;
import entity.Tweet;
import entity.TweetComment;
import entity.User;

import java.util.List;

public interface TweetCommentRepository extends BaseRepository<TweetComment, Long> {

    List<TweetComment> findByTweet(Tweet tweet);

    List<TweetComment> findByUser(User user);


}
