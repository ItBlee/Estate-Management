package com.itblee.repository.sqlbuilder.impl;

import com.itblee.repository.sqlbuilder.SqlExecutor;
import com.itblee.repository.sqlbuilder.SqlExtractor;
//import com.itblee.utils.ConnectionUtils;

import java.util.List;

public class SqlExecutorImpl implements SqlExecutor {

    private final SqlExtractor extractor;

    public SqlExecutorImpl(SqlExtractor extractor) {
        this.extractor = extractor;
    }

    @Override
    public <T> List<T> executeQuery(String sql, Class<T> entityClass, Object... params) {
        throw new UnsupportedOperationException();
        /*if (extractor == null)
            throw new UnsupportedOperationException();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionUtils.createConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ConnectionUtils.setParameter(statement, params);
            resultSet = statement.executeQuery();
            return extractor.extractData(resultSet, entityClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorRepositoryException(e);
        } finally {
            ConnectionUtils.close(resultSet);
        }*/
    }

    @Override
    public Object executeInsert(String sql, Object... params) {
        throw new UnsupportedOperationException();
        /*ResultSet resultSet = null;
        try (Connection connection = ConnectionUtils.createConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            try {
                connection.setAutoCommit(false);
                ConnectionUtils.setParameter(statement, params);
                statement.executeUpdate();
                resultSet = statement.getGeneratedKeys();
                Object id = null;
                if (resultSet.next()) {
                    int firstColumnIndex = 1;
                    id = resultSet.getObject(firstColumnIndex);
                }
                connection.commit();
                return id;
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new ErrorRepositoryException(e);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ErrorRepositoryException(e);
        } finally {
            ConnectionUtils.close(resultSet);
        }*/
    }

    @Override
    public void executeUpdate(String sql, Object... params) {
        throw new UnsupportedOperationException();
        /*try (Connection connection = ConnectionUtils.createConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                connection.setAutoCommit(false);
                ConnectionUtils.setParameter(statement, params);
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new ErrorRepositoryException(e);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ErrorRepositoryException(e);
        }*/
    }

}
