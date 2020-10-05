package dao.impl;

import dao.AbstractCrudDao;
import dao.DBConnector;
import dao.UserDao;
import dao.exception.SqlQueryExecutionException;
import entities.Role;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_USERNAME_QUERY = "SELECT * FROM users WHERE username = ?";
    private static final String SAVE_QUERY =
            "INSERT INTO users (username, password, first_name, last_name, email, phone_number, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE_QUERY =
            "UPDATE users SET username = ?, password = ?, email = ?, phone_number = ?, role = ? where id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";

    public UserDaoImpl(DBConnector dbConnector) {
        super(dbConnector, FIND_BY_ID_QUERY, SAVE_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getUsername());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getFirstName());
        preparedStatement.setString(4, entity.getLastName());
        preparedStatement.setString(5, entity.getEmail());
        preparedStatement.setString(6, entity.getPhoneNumber());
        preparedStatement.setString(7, entity.getRole().toString());

    }

    @Override
    protected void update(PreparedStatement preparedStatement, User entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(6, entity.getId());
    }

    @Override
    protected User buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder()
                .setId(resultSet.getLong("id"))
                .setUserName(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setEmail(resultSet.getString("email"))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setRole(Role.valueOf(resultSet.getString("role")))
                .build();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return findByParam(username, FIND_BY_USERNAME_QUERY);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setObject(1, userId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            String message = String.format("Fail to execute delete by id query by id: %d", userId);
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
