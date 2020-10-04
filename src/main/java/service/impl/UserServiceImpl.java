package service.impl;

import dao.UserDao;
import entities.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean register(User user) {
        return userDao.save(user);
    }
}
