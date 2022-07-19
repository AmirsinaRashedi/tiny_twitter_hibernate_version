package entity;


import base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = TweetComment.TABLE_NAME)
public class TweetComment extends BaseEntity<Long> {

    public static final String TABLE_NAME = "comment";

    @ManyToOne
    private User sender;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    @Column(name = "body")
    private String body;

    @ManyToOne
    private Tweet tweet;

    public TweetComment() {

    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {

        return "\n" +
                sender.getUsername() +
                "      " +
                sendTime.toString() +
                "\n" +
                body +
                "\n";

    }
}
