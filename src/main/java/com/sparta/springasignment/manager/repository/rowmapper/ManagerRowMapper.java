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
        return Manager.builder()
            .id(rs.getLong("manager_id"))
            .name(rs.getString("name"))
            .email(rs.getString("email"))
            .createdTime(rs.getTimestamp("created_time")
                .toLocalDateTime())
            .updatedTime(rs.getTimestamp("updated_time")
                .toLocalDateTime())
            .build();
    }
}
