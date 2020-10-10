package dao;

import entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserDao extends CrudDao<User> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean deleteUserById(Long userId);

    Map<String, Double> findAllSpecialistsWithRates();
}
