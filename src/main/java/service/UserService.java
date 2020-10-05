package service;

import dto.UserDto;
import entity.User;

public interface UserService {
    boolean register(UserDto userDto);

    User login(String username, String password);
}
