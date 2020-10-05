package service.impl;

import dao.UserDao;
import entities.User;
import service.UserService;
import service.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;

    public UserServiceImpl(UserDao userDao, UserValidator userValidator) {
        this.userDao = userDao;
        this.userValidator = userValidator;
    }

    @Override
    public boolean register(User user) {
        if (!userValidator.isValid(user) || isEmailNotUnique(user.getEmail())) {
            return false;
        }
        return userDao.save(user);
    }

    @Override
    public User login(String username, String password) {
        Optional<User> userFromDb = userDao.findByUsername(username);
        if (!userFromDb.isPresent() || !userFromDb.get().getPassword().equals(password)) {
            return null;
        }
        return userFromDb.get();
    }

    private boolean isEmailNotUnique(String email) {
        return userDao.findByEmail(email).isPresent();
    }

}
