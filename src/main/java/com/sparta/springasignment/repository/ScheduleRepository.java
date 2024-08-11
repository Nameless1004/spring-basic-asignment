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
    private final ScheduleRepositorySQL sql;

    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public Long save(Schedule schedule) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql.save(), Statement.RETURN_GENERATED_KEYS);
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
        jdbcTemplate.update(sql.update(), schedule.getManagerId(), schedule.getPassword(), schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime(), schedule.getScheduleId());
    }


    public Optional<Schedule> findScheduleById(Long scheduleId) {
        try {
            return jdbcTemplate.queryForObject(sql.findById(), new RowMapper<Optional<Schedule>>() {
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
        try {
            List<Schedule> schedules = jdbcTemplate.query(sql.findAll(), new RowMapper<Schedule>() {
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

    public List<Schedule> findSchedulesByPage(Integer page, Integer size) {
        int limit = size;
        int offset = (page - 1) * size;

        List<Schedule> schedules = jdbcTemplate.query(sql.findByPage(), new RowMapper<Schedule>() {
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
        },limit, offset);

        return schedules;
    }

    public void delete(Long scheduleId) {
        jdbcTemplate.update(sql.delete(), scheduleId);
    }
}
