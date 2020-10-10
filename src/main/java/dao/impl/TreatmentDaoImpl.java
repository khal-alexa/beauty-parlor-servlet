package dao.impl;

import dao.AbstractCrudDao;
import dao.DBConnector;
import dao.TreatmentDao;
import dao.exception.SqlQueryExecutionException;
import dto.TreatmentDto;
import entity.Treatment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreatmentDaoImpl extends AbstractCrudDao<Treatment> implements TreatmentDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM services WHERE id=?";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM services WHERE name=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM services";
    private static final String FIND_ALL_WITH_SPECIALISTS_QUERY = "select t.name, t.price, u.username from treatments t\n" +
            "left join specialists_treatments st on t.id = st.treatment_id\n" +
            "left join users u on st.specialist_id = u.id";
    private static final String INSERT_QUERY = "INSERT INTO services (name, description, price) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE services SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM services WHERE id = ?";

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
        List<TreatmentDto> dtos = new ArrayList<>();
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(FIND_ALL_WITH_SPECIALISTS_QUERY)) {
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
                    "Fail to execute findAll treatments with specialists query";
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
