package com.sparta.springasignment.repository;

import com.sparta.springasignment.entity.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ManagerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public Long save(Manager manager) {
        if (manager.getId() == null) {
            String sql = "INSERT INTO managers (name, email, created_time, updated_time) VALUES(?,?,?,?)";
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, manager.getName());
                preparedStatement.setString(2, manager.getEmail());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(manager.getCreatedTime()));
                preparedStatement.setTimestamp(4, Timestamp.valueOf(manager.getUpdatedTime()));
                return preparedStatement;
            }, keyHolder);
        }
        return keyHolder.getKey().longValue();
    }

    public void update(Manager manager) {
        String sql = "UPDATE managers SET name = ?, email = ?, create_time = ?, updated_time = ? where manager_id = ?";
        jdbcTemplate.update(sql, manager.getName(), manager.getEmail(), manager.getCreatedTime(), manager.getUpdatedTime(), manager.getId());
    }

    public Optional<Manager> findManagerById(Long id) {
        String sql = "SELECT * FROM managers WHERE manager_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Manager>>() {
                @Override
                public Optional<Manager> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Manager manager = new Manager(rs.getLong("manager_id"), rs.getString("name"), rs.getString("email"), rs.getTimestamp("created_time").toLocalDateTime(), rs.getTimestamp("updated_time").toLocalDateTime());
                    return Optional.of(manager);
                }
            }, id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Manager> findAllManagers() {
        String sql = "SELECT * FROM managers";
        List<Manager> managers = jdbcTemplate.query(sql, new RowMapper<Manager>() {
            @Override
            public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
                Manager manager = new Manager();
                manager.setId(rs.getLong("manager_id"));
                manager.setName(rs.getString("name"));
                manager.setEmail(rs.getString("email"));
                manager.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
                manager.setUpdatedTime(rs.getTimestamp("updated_time").toLocalDateTime());

                return manager;
            }
        });

        return managers;
    }

    public void delete(Long managerId) {
        String sql = "DELETE FROM managers WHERE manager_id = ?";
        jdbcTemplate.update(sql, managerId);
    }
}
