package com.example.lab_55.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDao{

    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists usr\n" +
                "(\n" +
                "    id       bigserial primary key,\n" +
                "    name     varchar,\n" +
                "    password    varchar,\n" +
                "    email    varchar\n" +
                ");");
    }
    public List<User> findAll() {
        String sql = "select * from usr";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void saveAll(List<User> users) {
        String sql = "insert into usr(email, name) " +
                "values(?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getEmail());
                ps.setString(2, users.get(i).getName());
            }

            public int getBatchSize() {
                return users.size();
            }
        });
    }

    public void deleteAll() {
        String sql = "delete from usr";
        jdbcTemplate.update(sql);
    }
}
