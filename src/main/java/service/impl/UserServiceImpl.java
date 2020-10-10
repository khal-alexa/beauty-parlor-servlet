package service.impl;

import dao.UserDao;
import dto.UserDto;
import entity.User;
import mapper.Mapper;
import service.UserService;
import service.encoder.PasswordEncoder;
import service.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final Mapper<User, UserDto> userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, UserValidator userValidator, Mapper<User,
            UserDto> userMapper, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.passwordEncoder = encoder;
    }

    @Override
    public boolean register(UserDto userDto) {
        if (!userValidator.isValid(userDto) || isEmailNotUnique(userDto.getEmail())) {
            return false;
        }
        return userDao.save(userMapper.mapDtoIntoEntity(encodePassword(userDto)));
    }

    @Override
    public User login(String username, String password) {
        Optional<User> userFromDb = userDao.findByUsername(username);
        if (!userFromDb.isPresent() || !passwordEncoder.verify(password, userFromDb.get().getPassword())) {
            return null;
        }
        return userFromDb.get();
    }

    private boolean isEmailNotUnique(String email) {
        return userDao.findByEmail(email).isPresent();
    }

    private UserDto encodePassword(UserDto userDto) {
        return new UserDto.UserDtoBuilder(userDto)
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }

}
