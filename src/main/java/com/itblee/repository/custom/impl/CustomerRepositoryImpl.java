package com.itblee.repository.custom.impl;

import com.itblee.exception.ErrorRepositoryException;
import com.itblee.repository.custom.CustomerRepositoryCustom;
import com.itblee.repository.entity.Customer;
import com.itblee.repository.sqlbuilder.SqlBuilder;
import com.itblee.repository.sqlbuilder.SqlMap;
import com.itblee.repository.sqlbuilder.impl.SqlBuilderFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findByCondition(Map<?, ?> conditions, Pageable pageable) {
        SqlMap<?> statements = (SqlMap<?>) conditions;
        SqlBuilderFactory factory = SqlBuilderFactory.newInstance("query", statements);
        SqlBuilder builder = factory.getBuilder();
        //builder.limit(pageable.getPageSize());
        //builder.offset((int) pageable.getOffset());
        try {
            String sql = builder.build();
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            return query.setMaxResults(pageable.getPageSize())
                    .setFirstResult((int) pageable.getOffset())
                    .getResultList();
        } catch (SQLSyntaxErrorException e) {
            throw new ErrorRepositoryException(e);
        }
    }

    @Override
    public int countByCondition(Map<?, ?> conditions) {
        SqlMap<?> statements = (SqlMap<?>) conditions;
        SqlBuilderFactory factory = SqlBuilderFactory.newInstance("query", statements);
        SqlBuilder builder = factory.getBuilder();
        try {
            String sql = builder.build();
            Query query = entityManager.createNativeQuery(sql);
            return ((Number) query.getSingleResult()).intValue();
        } catch (SQLSyntaxErrorException e) {
            throw new ErrorRepositoryException(e);
        }
    }

}
