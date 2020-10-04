package service.impl;

import dao.UserDao;
import entities.User;
import service.UserService;
import service.validator.UserValidator;

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

    private boolean isEmailNotUnique(String email) {
        return userDao.findByEmail(email).isPresent();
    }

}
