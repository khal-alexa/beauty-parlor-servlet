package dao;

import exception.SqlQueryExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudDao<E> implements CrudDao<E> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractCrudDao.class);

    private final String findByIdQuery;
    private final String saveQuery;
    private final String findAllQuery;
    private final String updateQuery;
    private final String deleteByIdQuery;
    protected DBConnector dbConnector;

    public AbstractCrudDao(DBConnector dbConnector, String findByIdQuery, String saveQuery, String findAllQuery,
                           String updateQuery, String deleteByIdQuery) {
        this.dbConnector = dbConnector;
        this.findByIdQuery = findByIdQuery;
        this.saveQuery = saveQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
        this.deleteByIdQuery = deleteByIdQuery;
    }

    public boolean save(E entity) {
        boolean isSaved;
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(saveQuery)) {
            insert(preparedStatement, entity);
            preparedStatement.execute();
            isSaved = true;
        } catch (SQLException e) {
            String message = String.format("Fail to execute query: %s", saveQuery);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
        return isSaved;
    }

    public Optional<E> findById(Long id) {
        return findByParam(id, findByIdQuery);
    }

    public <P> Optional<E> findByParam(P param, String findByParam) {
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(findByParam)) {
            preparedStatement.setObject(1, param);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.ofNullable(buildEntityFromResultSet(resultSet)) : Optional.empty();
            }
        } catch (
                SQLException e) {
            String message = String.format("Fail to execute find by param query %s with param: %s", findByIdQuery, param);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    public List<E> findAll(Page page) {
        int limit = page.getItemsPerPage();
        int offset = (page.getPageNumber() - 1) * limit;
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(findAllQuery)) {
            preparedStatement.setObject(1, limit);
            preparedStatement.setObject(2, offset);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(buildEntityFromResultSet(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            String message =
                    String.format("Fail to execute findAll query %s with params, LIMIT: %d; OFFSET: %d", findAllQuery, limit, offset);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    public void update(E entity) {
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(updateQuery)) {
            update(preparedStatement, entity);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                String message = (String.format("Failed to update entity for query: \"%s\"", updateQuery));
                throw new SqlQueryExecutionException(message);
            }
        } catch (SQLException e) {
            String message = String.format("Fail to execute query: %s", updateQuery);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    public void deleteById(Integer id) {
        try (final PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(deleteByIdQuery)) {
            preparedStatement.setObject(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            String message = String.format("Fail to execute delete by id query by id: %d", id);
            LOGGER.warn(message, e);
            throw new SqlQueryExecutionException(message, e);
        }
    }

    protected abstract void insert(PreparedStatement preparedStatement, E entity) throws SQLException;
    protected abstract void update(PreparedStatement preparedStatement, E entity) throws SQLException;
    protected abstract E buildEntityFromResultSet(ResultSet resultSet) throws SQLException;

}
