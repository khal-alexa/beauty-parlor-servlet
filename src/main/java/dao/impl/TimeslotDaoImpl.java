package dao.impl;

import dao.AbstractCrudDao;
import dao.DBConnector;
import entity.Timeslot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class TimeslotDaoImpl extends AbstractCrudDao<Timeslot> {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM timeslots WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO timeslots (start_time) VALUES (?)";
    private static final String FIND_ALL_QUERY = "SELECT * FROM timeslots LIMIT ? OFFSET ?";
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

}
