package dao;

import entities.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean deleteUserById(Long userId);
}
