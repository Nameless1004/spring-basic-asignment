package com.sparta.springasignment.manager.repository.rowmapper;

import com.sparta.springasignment.manager.entity.Manager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ManagerRowMapper implements RowMapper<Manager> {
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
}
