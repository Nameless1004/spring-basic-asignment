package com.sparta.springasignment.repository;

import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public Long save(Schedule schedule) {
        String sql = "INSERT INTO schedules (manager_id, password, contents, created_time, updated_time) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, schedule.getManagerId());
            preparedStatement.setString(2, schedule.getPassword());
            preparedStatement.setString(3, schedule.getContents());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(schedule.getCreatedTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getUpdatedTime()));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void update(Schedule schedule) {
        String sql = "UPDATE schedules SET manager_id = ?, password = ?, contents = ?, created_time = ?, updated_time = ? where schedule_id = ?";
        jdbcTemplate.update(sql, schedule.getManagerId(), schedule.getPassword(), schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime(), schedule.getScheduleId());
    }


    public Optional<Schedule> findScheduleById(Long scheduleId) {
        String sql = "SELECT * FROM schedules WHERE schedule_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Schedule>>() {
                @Override
                public Optional<Schedule> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Schedule schedule = new Schedule(rs.getLong("schedule_id"), rs.getLong("manager_id"), rs.getString("password"), rs.getString("contents"), rs.getTimestamp("created_time").toLocalDateTime(), rs.getTimestamp("updated_time").toLocalDateTime());
                    return Optional.of(schedule);
                }
            }, scheduleId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Schedule> findAllSchedulesByQuery(String query) {
        try {
            List<Schedule> schedules = jdbcTemplate.query(query, new RowMapper<Schedule>() {
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
            });
            return schedules;

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Schedule> findAllSchedules() {
        String sql = "SELECT * FROM schedules";

        try {
            List<Schedule> schedules = jdbcTemplate.query(sql, new RowMapper<Schedule>() {
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
            });
            return schedules;

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    public void delete(Long scheduleId) {
        String sql = "DELETE FROM managers WHERE manager_id = ?";
        jdbcTemplate.update(sql, scheduleId);
    }
}
