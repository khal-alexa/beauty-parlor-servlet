package dao.impl;

import dao.AbstractCrudDao;
import dao.DBConnector;
import dao.TimeslotDao;
import entity.Timeslot;
import exception.SqlQueryExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

public class TimeslotDaoImpl extends AbstractCrudDao<Timeslot> implements TimeslotDao {
    private static final Logger LOGGER = LogManager.getLogger(TimeslotDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM timeslots WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO timeslots (start_time) VALUES (?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM timeslots LIMIT ? OFFSET ?";
    private static final String FIND_BY_START_TIME_QUERY = "select * from timeslots where start_time=?";
    private static final String UPDATE_QUERY =
            "UPDATE timeslots SET start_time = ? where id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM timeslots WHERE id = ?";

    public TimeslotDaoImpl(DBConnector dbConnector) {
        super(dbConnector, FIND_BY_ID_QUERY, SAVE_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Timeslot entity) throws SQLException {
        preparedStatement.setTime(1, Time.valueOf(entity.getStartTime()));
    }

    @Override
    protected void update(PreparedStatement preparedStatement, Timeslot entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    protected Timeslot buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Timeslot(resultSet.getLong("id"),
                resultSet.getTime("start_time").toLocalTime());
    }

    @Override
    public Timeslot findByStartTime(LocalTime time) {
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(FIND_BY_START_TIME_QUERY)) {
            preparedStatement.setTime(1, Time.valueOf(time.toString()));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Timeslot timeslot = new Timeslot();
                while (resultSet.next()) {
                    new Timeslot(resultSet.getLong("id"),
                            LocalTime.parse(resultSet.getTime("start_time").toString()));
                }
                return timeslot;
            }
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute find timeslot by start time query with parameter: %s", time);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }
}
