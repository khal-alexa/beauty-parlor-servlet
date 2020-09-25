package dao;

import entities.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User> {
    Optional<User> findByEmail(String email);

    boolean deleteUserById(Integer userId);
}
