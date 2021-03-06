package dao.impl;

import dao.AbstractCrudDao;
import dao.AppointmentDao;
import dao.DBConnector;
import dao.Page;
import exception.SqlQueryExecutionException;
import entity.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl extends AbstractCrudDao<Appointment> implements AppointmentDao {
    private static final Logger LOGGER = LogManager.getLogger(AppointmentDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM appointments WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM appointments LIMIT ? OFFSET ?";
    private static final String FIND_ALL_BY_DATE = "SELECT * FROM appointments WHERE date=? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_BY_DATE_AND_TREATMENT = "select * from appointments a where date=? and treatment_id=?";
    private static final String FIND_ALL_BY_DATE_AND_SPECIALIST = "select * from appointments a where date=? and specialist_id = ?";

    private static final String SAVE_QUERY = "INSERT INTO appointments (timeslot_id, date, client_id, specialist_id, treatment_id, is_paid, is_done) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE appointments SET timeslot_id = ?, date = ?, client_id = ?, specialist_id, treatment_id = ?, is_paid = ?, is_done = ? WHERE id = ?";
    private static final String UPDATE_DONE = "UPDATE appointments SET is_done = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM appointments WHERE id = ?";

    public AppointmentDaoImpl(DBConnector dbConnector) {
        super(dbConnector, FIND_BY_ID_QUERY, SAVE_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected Appointment buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Appointment.Builder()
                .setId(resultSet.getLong("id"))
                .setTimeslotId(resultSet.getLong("timeslot_id"))
                .setDate(resultSet.getDate("date").toLocalDate())
                .setClientId(resultSet.getLong("client_id"))
                .setSpecialistId(resultSet.getLong("specialist_id"))
                .setTreatmentId(resultSet.getLong("treatment_id"))
                .setPaid(resultSet.getBoolean("is_paid"))
                .setDone(resultSet.getBoolean("is_done"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Appointment entity) throws SQLException {
        preparedStatement.setLong(1, entity.getTimeslotId());
        preparedStatement.setDate(2, Date.valueOf(entity.getDate()));
        preparedStatement.setLong(3, entity.getClientId());
        preparedStatement.setLong(4, entity.getSpecialistId());
        preparedStatement.setLong(5, entity.getTreatmentId());
        preparedStatement.setBoolean(6, entity.isPaid());
        preparedStatement.setBoolean(7, entity.isDone());
    }

    @Override
    protected void update(PreparedStatement preparedStatement, Appointment entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(8, entity.getId());
    }

    private List<Appointment> findAllByParam(Object param, Page page, String query) {
        int limit = page.getItemsPerPage();
        int offset = (page.getPageNumber() - 1) * limit;
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, param);
            preparedStatement.setObject(2, limit);
            preparedStatement.setObject(3, offset);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Appointment> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(buildEntityFromResultSet(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute findAll appointments query with params, param: %s; LIMIT: %d; OFFSET: %d", param, limit, offset);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    @Override
    public List<Appointment> findAllByDate(LocalDate date, Page page) {
        return findAllByParam(date, page, FIND_ALL_BY_DATE);
    }

    @Override
    public List<Appointment> findAllByDateAndSpecialist(LocalDate date, Long specialistId) {
        try (final Connection connection = dbConnector.getConnection();
                final PreparedStatement preparedStatement = connection.
                prepareStatement(FIND_ALL_BY_DATE_AND_SPECIALIST)) {
            return findByTwoParams(date, specialistId, preparedStatement);
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute findAll appointments by date and specialist query with params: %s, %s", date, specialistId);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    @Override
    public List<Appointment> findAllByDateAndTreatmentId(LocalDate date, Long treatmentId) {
        try (Connection connection = dbConnector.getConnection();
                final PreparedStatement preparedStatement = connection.
                prepareStatement(FIND_ALL_BY_DATE_AND_TREATMENT)) {
            return findByTwoParams(date, treatmentId, preparedStatement);
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute findAll appointments query with params: %s, %s", date, treatmentId);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    private List<Appointment> findByTwoParams(LocalDate param1, Long param2, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setObject(1, param1);
        preparedStatement.setLong(2, param2);
        try (final ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Appointment> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(buildEntityFromResultSet(resultSet));
            }
            return entities;
        }
    }

    @Override
    public boolean updateDone(Long id) {
        try (final Connection connection = dbConnector.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DONE)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
                return true;
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute update appointment, set done=true, query with id: %s: ", id);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
