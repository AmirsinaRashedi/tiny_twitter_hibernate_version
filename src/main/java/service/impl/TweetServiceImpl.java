package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Tweet;
import entity.User;
import repository.TweetRepository;
import service.TweetService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TweetServiceImpl extends BaseServiceImpl<Tweet, Long, TweetRepository>
        implements TweetService {

    public TweetServiceImpl(TweetRepository repository) {

        super(repository);

    }

    @Override
    public List<Tweet> getByUser(User user) {

        List<Tweet> userTweets;

        try {

            userTweets = repository.findByUser(user);

        } catch (Exception e) {

            return null;

        }

        return userTweets;

    }

    @Override
    public Tweet createTweet(User user) {

        Tweet newTweet = new Tweet();

        try (Scanner stringInput = new Scanner(System.in)) {

            newTweet.setSender(user);

            String tweetBody;

            System.out.println("write your tweet (max 280 characters):");

            tweetBody = stringInput.nextLine();

            if (tweetBody.length() > 280)
                throw new StringIndexOutOfBoundsException();

            newTweet.setBody(tweetBody);

            newTweet.setSendTime(LocalDateTime.now());

            newTweet.setLikes(new ArrayList<>());

//            newTweet.setComments(new ArrayList<>());

        } catch (Exception e) {

            return null;

        }

        newTweet = save(newTweet);

        return newTweet;
    }

    @Override
    public Tweet likeTweet(User user) {

        List<Tweet> allTweets = findAll();

        int tweetCount = 0;

        try {

            for (Tweet tweet : allTweets) {

                System.out.println(++tweetCount + "- " + tweet);

            }

        } catch (NullPointerException e) {

            System.out.println("no tweets available");

            return null;

        }


        Tweet likedTweet;

        try (Scanner intInput = new Scanner(System.in)) {

            System.out.print("enter the id of the tweet you like to like: ");

            int chosenTweet = intInput.nextInt();

            if (chosenTweet > 0 && chosenTweet <= tweetCount) {

                List<User> likes = allTweets.get(chosenTweet - 1).getLikes();

                for (User u : likes) {

                    if (u.getUsername().equals(user.getUsername())) {

                        System.out.println("this user has already liked this tweet");

                        return allTweets.get(chosenTweet - 1);

                    }

                }

                repository.beginTransaction();

                likedTweet = repository.likeTweet(allTweets.get(chosenTweet - 1), user);

                repository.commitTransaction();

            } else
                throw new Exception();

        } catch (Exception e) {

            System.out.println("unable to like the tweet");

            repository.rollbackTransaction();

            return null;

        }

        return likedTweet;

    }

    @Override
    public Tweet removeLike(User user) {

        List<Tweet> allTweets = findAll();

        int tweetCount = 0;

        try {

            allTweets = allTweets.stream().filter(e -> e.getLikes().contains(user)).toList();

            if (allTweets.size() == 0) {

                System.out.println("you have not liked any tweets");

                return null;

            }

            for (Tweet tweet : allTweets) {

                System.out.println(++tweetCount + "- " + tweet);

            }

        } catch (NullPointerException e) {

            System.out.println("no tweets available");

            return null;

        }

        Tweet dislikedTweet;

        try (Scanner intInput = new Scanner(System.in)) {

            System.out.print("enter the id of the tweet you like to remove like from: ");

            int chosenTweet = intInput.nextInt();

            if (chosenTweet > 0 && chosenTweet <= tweetCount) {


                repository.beginTransaction();

                dislikedTweet = repository.removeLike(allTweets.get(chosenTweet - 1), user);

                repository.commitTransaction();

            } else
                throw new Exception();

        } catch (Exception e) {

            System.out.println("unable to like the tweet");

            repository.rollbackTransaction();

            return null;

        }

        return dislikedTweet;


    }


}
