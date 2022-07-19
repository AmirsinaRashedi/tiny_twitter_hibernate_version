package service;

import base.service.BaseService;
import entity.TweetComment;
import entity.User;
import repository.TweetCommentRepository;

import java.util.List;

public interface TweetCommentService extends BaseService<TweetComment, Long, TweetCommentRepository> {

    List<TweetComment> getByTweet();

    TweetComment createComment(User user);

    List<TweetComment> findByUser(User user);

    void deleteComment(User user);

    TweetComment editComment(User user);

}
