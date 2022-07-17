package service;

import base.service.BaseService;
import entity.User;
import repository.UserRepository;

public interface UserService extends BaseService<User, Long, UserRepository> {

    User findByUsername(String username);

    User createNewUser();

}
