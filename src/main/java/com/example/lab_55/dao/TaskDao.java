package com.example.lab_55.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TaskDao extends BaseDao{
    public TaskDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists tasks\n" +
                "(\n" +
                "    id       bigserial primary key,\n" +
                "    header     varchar,\n" +
                "    description     varchar,\n" +
                "    date_perform    varchar,\n" +
                "    task_owner    varchar,\n" +
                "    status    varchar\n" +
                ");");
    }
    public List<Task> findAll() {
        String sql = "select * from tasks";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
    }

    public void saveAll(List<Task> tasks) {
        String sql = "insert into tasks(id, header, status, date_perform) " +
                "values(?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, tasks.get(i).getId());
                ps.setString(2, tasks.get(i).getHeader());
                ps.setString(3, tasks.get(i).getStatus());
                ps.setString(4, tasks.get(i).getDatePerform());
            }

            public int getBatchSize() {
                return tasks.size();
            }
        });
    }

    public void deleteAll() {
        String sql = "delete from tasks";
        jdbcTemplate.update(sql);
    }

}
