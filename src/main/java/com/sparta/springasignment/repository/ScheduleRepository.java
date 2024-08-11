package com.sparta.springasignment.repository;

import com.sparta.springasignment.entity.Schedule;
import com.sparta.springasignment.repository.rowmapper.ScheduleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ScheduleRepositorySQL sql;
    private final ScheduleRowMapper rowMapper;

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
            Schedule schedule = jdbcTemplate.queryForObject(sql.findById(), rowMapper, scheduleId);
            return Optional.ofNullable(schedule);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Schedule> findAllSchedulesByQuery(String query) {
        try{
        List<Schedule> schedules = jdbcTemplate.query(query, rowMapper);
        return schedules;
        } catch(DataAccessException e){
            return new ArrayList<>();
        }
    }

    public List<Schedule> findAllSchedules() {
        try {
            List<Schedule> schedules = jdbcTemplate.query(sql.findAll(), rowMapper);
            return schedules;
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Schedule> findSchedulesByPage(Integer page, Integer size) {
        try {
            int limit = size;
            int offset = (page - 1) * size;

            List<Schedule> schedules = jdbcTemplate.query(sql.findByPage(), rowMapper, limit, offset);

            return schedules;
        } catch (DataAccessException e){
            return new ArrayList<>();
        }
    }

    public void delete(Schedule schedule) {
        jdbcTemplate.update(sql.delete(), schedule.getScheduleId());
    }
}
