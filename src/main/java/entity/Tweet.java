package entity;

import base.domain.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = Tweet.TABLE_NAME)
public class Tweet extends BaseEntity<Long> {

    public static final String TABLE_NAME = "tweet";

    @ManyToOne()
    private User sender;

    @Column(columnDefinition = "varchar(280)")
    private String body;

    private LocalDateTime sendTime;

//    @OneToMany(mappedBy:"")
//    private List<TweetComment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "likes")
    private List<User> likes;

    public Tweet() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    //    public List<TweetComment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<TweetComment> comments) {
//        this.comments = comments;
//    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {

        StringBuilder tweetBody = new StringBuilder();

        tweetBody.append("\n");
        tweetBody.append(sender.getUsername());
        tweetBody.append("      ");
        tweetBody.append(sendTime.toString());
        tweetBody.append("\n");
        tweetBody.append(body);
        tweetBody.append("\n");
        tweetBody.append("Likes: ");
        tweetBody.append(likes.size());
        tweetBody.append("\n");
//        comments.foreach(e-> tweetBody.append(e.toString() + "\n"));
        tweetBody.append("\n");

        return tweetBody.toString();

    }

}
