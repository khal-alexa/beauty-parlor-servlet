package dao.impl;

import dao.AbstractCrudDao;
import dao.DBConnector;
import dao.FeedbackDao;
import dao.Page;
import dao.exception.SqlQueryExecutionException;
import entities.Feedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDaoImpl extends AbstractCrudDao<Feedback> implements FeedbackDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM feedbacks WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM feedbacks OFFSET ? LIMIT ?";
    private static final String FIND_ALL_BY_CLIENT_QUERY = "SELECT * FROM feedbacks WHERE client_id = ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_BY_SPECIALIST_QUERY = "SELECT * FROM feedbacks WHERE specialist_id = ? OFFSET ? LIMIT ?";

    private static final String SAVE_QUERY = "INSERT INTO feedbacks (rate, text, client_id, specialist_id, date_time) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE feedbacks SET rate=?, text = ?, client_id = ?, specialist_id = ?, dateTime = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM feedbacks WHERE id = ?";

    public FeedbackDaoImpl(DBConnector dbConnector) {
        super(dbConnector, FIND_BY_ID_QUERY, SAVE_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Feedback entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRate());
        preparedStatement.setString(2, entity.getText());
        preparedStatement.setLong(3, entity.getClientId());
        preparedStatement.setLong(4, entity.getSpecialistId());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(entity.getDateTime()));
    }

    @Override
    protected void update(PreparedStatement preparedStatement, Feedback entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(6, entity.getId());
    }

    @Override
    protected Feedback buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Feedback.FeedbackBuilder()
                .setId(resultSet.getLong("id"))
                .setText(resultSet.getString("text"))
                .setRate(resultSet.getInt("rate"))
                .setClientId(resultSet.getLong("client_id"))
                .setSpecialistId(resultSet.getLong("specialist_id"))
                .setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime())
                .build();
    }

    @Override
    public List<Feedback> findAllByClientId(Long clientId, Page page) {
        return findAllByParam(clientId, page, FIND_ALL_BY_CLIENT_QUERY);
    }

    @Override
    public List<Feedback> findAllBySpecialistId(Long specialistId, Page page) {
        return findAllByParam(specialistId, page, FIND_ALL_BY_SPECIALIST_QUERY);
    }

    private List<Feedback> findAllByParam(Object param, Page page, String query) {
        int limit = page.getItemsPerPage();
        int offset = (page.getPageNumber() - 1) * limit;
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, param);
            preparedStatement.setObject(2, limit);
            preparedStatement.setObject(3, offset);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Feedback> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(buildEntityFromResultSet(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute findAll query with params, param: %s; LIMIT: %d; OFFSET: %d", param, limit, offset);
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
