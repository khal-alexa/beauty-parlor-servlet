package dao.implemenations;

import dao.AbstractCrudDao;
import dao.ServiceDao;
import entities.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ServiceDaoImpl extends AbstractCrudDao<Service> implements ServiceDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM services WHERE id=?";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM services WHERE name=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM services";
    private static final String INSERT_QUERY = "INSERT INTO services (name, description, price) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE services SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM services WHERE id = ?";

    public ServiceDaoImpl() {
        super(FIND_BY_ID_QUERY, INSERT_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Service entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setBigDecimal(3, entity.getPrice());
    }

    @Override
    protected void update(PreparedStatement preparedStatement, Service entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(4, entity.getId());
    }

    @Override
    protected Service buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Service.ServiceBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setPrice(resultSet.getBigDecimal("price"))
                .build();
    }

    @Override
    public Optional<Service> findByName(String name) {
        return findByParam(name, FIND_BY_NAME_QUERY);
    }

}
