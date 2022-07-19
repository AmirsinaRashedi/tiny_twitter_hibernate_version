package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Tweet;
import entity.TweetComment;
import entity.User;
import repository.TweetCommentRepository;
import service.TweetCommentService;
import service.TweetService;
import util.ApplicationContext;
import util.Menu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TweetCommentServiceImpl extends BaseServiceImpl<TweetComment, Long, TweetCommentRepository>
        implements TweetCommentService {

    private TweetService tweetService;

    public TweetCommentServiceImpl(TweetCommentRepository repository) {

        super(repository);

        tweetService = ApplicationContext.getTweetService();

    }

    @Override
    public List<TweetComment> getByTweet() {

        List<Tweet> allTweets = tweetService.findAll();

        int tweetCount = 0;

        try {

            allTweets = allTweets.stream().filter(e -> e.getComments().size() > 0).toList();

            if (allTweets.size() == 0)
                throw new NullPointerException();

            for (Tweet tweet : allTweets) {

                System.out.println(++tweetCount + "- " + tweet);

            }

        } catch (NullPointerException e) {

            System.out.println("no tweets with comment available");

            return null;

        }

        List<TweetComment> foundComments;

        try (Scanner intInput = new Scanner(System.in)) {

            System.out.print("enter the id of the tweet you like to view the comments: ");

            int chosenTweet = intInput.nextInt();

            if (chosenTweet > 0 && chosenTweet <= tweetCount)
                foundComments = repository.findByTweet(allTweets.get(chosenTweet - 1));
            else
                throw new IndexOutOfBoundsException();

        } catch (Exception e) {

            return null;

        }

        return foundComments;

    }

    @Override
    public TweetComment createComment(User user) {

        Scanner stringInput = new Scanner(System.in);

        Scanner intInput = new Scanner(System.in);

        TweetComment newComment = new TweetComment();

        Tweet targetTweet;

        List<Tweet> allTweets = tweetService.findAll();

        int tweetCount = 0;

        try {

            for (Tweet tweet : allTweets) {

                System.out.println(++tweetCount + "- " + tweet);

            }

        } catch (NullPointerException e) {

            System.out.println("no tweets with comment available");

            return null;

        }

        try {

            System.out.print("enter the id of the tweet you like to comment on: ");

            int chosenTweet = intInput.nextInt();

            if (chosenTweet > 0 && chosenTweet <= tweetCount)
                targetTweet = allTweets.get(chosenTweet - 1);
            else
                throw new IndexOutOfBoundsException();

        } catch (Exception e) {

            return null;

        }

        try {

            newComment.setSender(user);

            String commentBody;

            System.out.println("write your comment :");

            commentBody = stringInput.nextLine();

            newComment.setBody(commentBody);

            newComment.setSendTime(LocalDateTime.now());

            newComment.setTweet(targetTweet);

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

        newComment = save(newComment);

        return newComment;

    }

    @Override
    public List<TweetComment> findByUser(User user) {

        try {

            return repository.findByUser(user);

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public void deleteComment(User user) {

        List<TweetComment> userComments = findByUser(user);

        int chosenComment;

        Scanner intInput = new Scanner(System.in);

        Menu.deleteCommentMenu();

        int choice = intInput.nextInt();

        try {

            switch (choice) {

                case 1: {

                    List<Tweet> userTweets = tweetService.getByUser(user);


                    try {

                        userTweets = userTweets.stream().filter(e -> e.getComments().size() > 0).toList();

                        if (userTweets.size() == 0) {

                            System.out.println("no tweets with comments found");

                            throw new NullPointerException();

                        }

                        int tweetCount = 0;

                        for (Tweet t : userTweets) {

                            System.out.println(++tweetCount + t.toString());

                        }

                        System.out.print("choose the tweet you want to remove comments from: ");

                        int chosenTweet = intInput.nextInt();

                        if (chosenTweet > 0 && chosenTweet <= tweetCount) {

                            List<TweetComment> commentsList = userTweets.get(chosenTweet - 1).getComments();

                            int commentCount = 0;

                            for (TweetComment tc : commentsList) {

                                System.out.println(++commentCount + tc.toString());

                            }

                            System.out.print("choose the comment you want to delete: ");

                            int commentToDelete = intInput.nextInt();

                            if (commentToDelete > 0 && commentToDelete <= commentCount) {

                                delete(commentsList.get(commentToDelete - 1));

                            } else
                                throw new IndexOutOfBoundsException();


                        } else
                            throw new IndexOutOfBoundsException();


                    } catch (Exception e) {

                        System.out.println("unable to delete");

                        break;

                    }


                    break;

                }

                case 2: {

                    try {

                        int commentCount = 0;

                        for (TweetComment tc : userComments) {

                            System.out.println(++commentCount + tc.toString());

                        }

                        System.out.print("choose the comment you like to delete: ");

                        chosenComment = intInput.nextInt();

                        if (chosenComment > 0 && chosenComment <= commentCount)
                            delete(userComments.get(chosenComment - 1));


                    } catch (Exception e) {

                        System.out.println("no comments found");

                        break;

                    }

                    break;

                }

                default: {

                    System.out.println("unable to delete");

                    break;

                }

            }

        } catch (Exception e) {

            System.out.println("cant delete");

        }


    }

    @Override
    public TweetComment editComment(User user) {

        List<TweetComment> userComments = findByUser(user);

        Scanner intInput = new Scanner(System.in);

        TweetComment commentToEdit;

        try {

            int commentCount = 0;

            for (TweetComment tc : userComments) {

                System.out.println(++commentCount + tc.toString());

            }

            System.out.print("enter the comment you want to edit: ");

            int chosenComment = intInput.nextInt();

            if (chosenComment > 0 && chosenComment <= commentCount) {

                commentToEdit = userComments.get(chosenComment - 1);

                Scanner stringInput = new Scanner(System.in);

                System.out.println("enter new body:");

                commentToEdit.setBody(stringInput.nextLine().concat("\nEDITED"));

                commentToEdit = save(commentToEdit);


            } else
                throw new IndexOutOfBoundsException();


        } catch (Exception e) {

            return null;

        }


        return commentToEdit;

    }

}
