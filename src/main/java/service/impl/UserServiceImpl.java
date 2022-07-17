package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.User;
import repository.UserRepository;
import service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {


    public UserServiceImpl(UserRepository userRepository) {

        super(userRepository);

    }

    @Override
    public User findByUsername(String username) {

        User foundUser;

        try {

            foundUser = repository.findByUsername(username);

        } catch (Exception e) {

            return null;

        }

        return foundUser;

    }

    @Override
    public User createNewUser() {

        User newUser = new User();

        try (Scanner stringInput = new Scanner(System.in)) {

            System.out.print("enter your username: ");

            String username = stringInput.nextLine();

            if (findByUsername(username) != null)
                throw new InputMismatchException("this username is taken");

            newUser.setUsername(username);

            System.out.print("enter your firstname: ");

            newUser.setFirstname(stringInput.nextLine());

            System.out.print("enter your lastname: ");

            newUser.setLastname(stringInput.nextLine());

            System.out.print("enter a password: ");

            newUser.setPassword(stringInput.nextLine());


        } catch (Exception e) {

            System.out.println(e.getMessage());

            e.printStackTrace();

            newUser = null;

        }

        newUser = save(newUser);

        return newUser;

    }

}
