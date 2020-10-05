package service;

import entities.User;

public interface UserService {
    boolean register(User user);

    User login(String username, String password);
}
