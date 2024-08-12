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
        Schedule schedule = new Schedule();
        schedule.setScheduleId(rs.getLong("schedule_id"));
        schedule.setManagerId(rs.getLong("manager_id"));
        schedule.setPassword(rs.getString("password"));
        schedule.setContents(rs.getString("contents"));
        schedule.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
        schedule.setUpdatedTime(rs.getTimestamp("updated_time").toLocalDateTime());

        return schedule;
    }
}
