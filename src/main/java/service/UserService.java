package service;

import dto.UserDto;
import entity.Role;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean register(UserDto userDto);

    Optional<User> login(String username, String password);

    boolean validatePasswords(String password, String confirmedPassword);

    List<String> findAllByRole(Role role);

}
