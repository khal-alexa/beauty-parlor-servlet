package dao.implemenations;

import dao.AbstractCrudDao;
import dao.AppointmentDao;
import dao.Page;
import dao.exception.SqlQueryExecutionException;
import entities.Appointment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl extends AbstractCrudDao<Appointment> implements AppointmentDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM appointments WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM appointments LIMIT ? OFFSET ?";
    private static final String FIND_ALL_BY_CLIENT = "SELECT * FROM appointments WHERE client_id = ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_BY_SPECIALIST = "SELECT * FROM appointments WHERE specialist_id = ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_BY_SERVICE = "SELECT * FROM appointments WHERE service_id = ? LIMIT ? OFFSET ?";

    private static final String SAVE_QUERY = "INSERT INTO appointments (timeslot_id, date, client_id, specialist_id, service_id, is_paid, is_done) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE appointments SET timeslot_id = ?, date = ?, client_id = ?, specialist_id, service_id = ?, is_paid = ?, is_done = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM appointments WHERE id = ?";

    public AppointmentDaoImpl() {
        super(FIND_BY_ID_QUERY, SAVE_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected Appointment buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Appointment.AppointmentBuilder()
                .setId(resultSet.getLong("id"))
                .setTimeslotId(resultSet.getLong("user_name"))
                .setDate(resultSet.getDate("date").toLocalDate())
                .setClientId(resultSet.getLong("client_id"))
                .setSpecialistId(resultSet.getLong("specialist_id"))
                .setServiceId(resultSet.getLong("service_id"))
                .setPaid(resultSet.getBoolean("is_paid"))
                .setDone(resultSet.getBoolean("is_done"))
                .build();
    }

    @Override
    public List<Appointment> findAllByClientId(Long clientId, Page page) {
        return findAllByParam(clientId, page, FIND_ALL_BY_CLIENT);
    }

    @Override
    public List<Appointment> findAllBySpecialistId(Long specialistId, Page page) {
        return findAllByParam(specialistId, page, FIND_ALL_BY_SPECIALIST);
    }

    @Override
    public List<Appointment> findAllByServiceId(Long serviceId, Page page) {
        return findAllByParam(serviceId, page, FIND_ALL_BY_SERVICE);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Appointment entity) throws SQLException {
        preparedStatement.setLong(1, entity.getTimeslotId());
        preparedStatement.setDate(2, Date.valueOf(entity.getDate()));
        preparedStatement.setLong(3, entity.getClientId());
        preparedStatement.setLong(4, entity.getSpecialistId());
        preparedStatement.setLong(5, entity.getServiceId());
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
        try (final PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(query)) {
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
                    String.format("Fail to execute findAll query with params, param: %s; LIMIT: %d; OFFSET: %d", param, limit, offset);
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
