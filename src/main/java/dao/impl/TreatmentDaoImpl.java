package dao.impl;

import dao.AbstractCrudDao;
import dao.DBConnector;
import dao.TreatmentDao;
import exception.SqlQueryExecutionException;
import dto.TreatmentDto;
import entity.Treatment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreatmentDaoImpl extends AbstractCrudDao<Treatment> implements TreatmentDao {
    private static final Logger LOGGER = LogManager.getLogger(TreatmentDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM treatments WHERE id=?";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM treatments WHERE name=?";
    private static final String FIND_ALL_QUERY = "select t.name, t.price, u.username from treatments t \n" +
            "            left join specialists_treatments st on t.id = st.treatment_id\n" +
            "            left join users u on st.specialist_id = u.id";
    private static final String FIND_ALL_WITH_SPECIALISTS_QUERY = "select t.name, t.price, u.username from treatments t\n" +
            "left join specialists_treatments st on t.id = st.treatment_id\n" +
            "left join users u on st.specialist_id = u.id LIMIT ? OFFSET ?";
    private static final String INSERT_QUERY = "INSERT INTO treatments (name, description, price) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE treatments SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM treatments WHERE id = ?";

    public TreatmentDaoImpl(DBConnector dbConnector) {
        super(dbConnector, FIND_BY_ID_QUERY, INSERT_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Treatment entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setBigDecimal(3, entity.getPrice());
    }

    @Override
    protected void update(PreparedStatement preparedStatement, Treatment entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(4, entity.getId());
    }

    @Override
    protected Treatment buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Treatment.ServiceBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setPrice(resultSet.getBigDecimal("price"))
                .build();
    }

    @Override
    public Optional<Treatment> findByName(String name) {
        return findByParam(name, FIND_BY_NAME_QUERY);
    }

    @Override
    public List<TreatmentDto> findAllWithSpecialists() {
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(FIND_ALL_QUERY)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<TreatmentDto> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(new TreatmentDto.TreatmentDtoBuilder()
                            .setTreatmentName(resultSet.getString(1))
                            .setPrice(resultSet.getBigDecimal(2))
                            .setSpecialistName(resultSet.getString(3))
                            .build());
                }
                return entities;
            }
        } catch (SQLException e) {
            String message =
                   "Fail to execute findAll treatments query";
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    @Override
    public List<TreatmentDto> findAllWithSpecialistsPaged(int limit, int offset) {
        List<TreatmentDto> dtos = new ArrayList<>();
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(FIND_ALL_WITH_SPECIALISTS_QUERY)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    dtos.add(new TreatmentDto.TreatmentDtoBuilder()
                            .setTreatmentName(resultSet.getString(1))
                            .setPrice(resultSet.getBigDecimal(2))
                            .setSpecialistName(resultSet.getString(3))
                            .build());
                }
                return dtos;
            }
        } catch (SQLException e) {
            String message =
                    "Fail to execute findAllPaged treatments with specialists query";
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
