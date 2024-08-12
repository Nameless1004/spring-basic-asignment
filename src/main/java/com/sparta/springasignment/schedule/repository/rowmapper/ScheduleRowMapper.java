package com.sparta.springasignment.schedule.repository.rowmapper;

import com.sparta.springasignment.schedule.entity.Schedule;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleRowMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Schedule.builder()
                       .scheduleId(rs.getLong("schedule_id"))
                       .managerId(rs.getLong("manager_id"))
                       .password(rs.getString("password"))
                       .contents(rs.getString("contents"))
                       .createdTime(rs.getTimestamp("created_time").toLocalDateTime())
                       .updatedTime(rs.getTimestamp("updated_time").toLocalDateTime())
                       .build();
    }
}
