package service.impl;

import dao.UserDao;
import dto.UserDto;
import entity.User;
import mapper.Mapper;
import service.UserService;
import service.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final Mapper<User, UserDto> userMapper;

    public UserServiceImpl(UserDao userDao, UserValidator userValidator, Mapper<User, UserDto> userMapper) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    @Override
    public boolean register(UserDto userDto) {
        if (!userValidator.isValid(userDto) || isEmailNotUnique(userDto.getEmail())) {
            return false;
        }
        return userDao.save(userMapper.mapDtoIntoEntity(userDto));
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
