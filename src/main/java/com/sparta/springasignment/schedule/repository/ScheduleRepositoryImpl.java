package com.sparta.springasignment.schedule.repository;

import com.sparta.springasignment.schedule.entity.Schedule;
import com.sparta.springasignment.schedule.repository.rowmapper.ScheduleRowMapper;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ScheduleRepositorySQL sql;
    private final ScheduleRowMapper rowMapper;

    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    @Override
    public Long save(Schedule schedule) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql.save(),
                Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, schedule.getManagerId());
            preparedStatement.setString(2, schedule.getPassword());
            preparedStatement.setString(3, schedule.getContents());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(schedule.getCreatedTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getUpdatedTime()));
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey())
            .longValue();
    }

    @Override
    public void update(Schedule schedule) {
        jdbcTemplate.update(sql.update(), schedule.getManagerId(), schedule.getPassword(),
            schedule.getContents(), schedule.getCreatedTime(), schedule.getUpdatedTime(),
            schedule.getScheduleId());
    }

    @Override
    public void delete(Schedule schedule) {
        jdbcTemplate.update(sql.delete(), schedule.getScheduleId());
    }

    @Override
    public Optional<Schedule> findById(Long scheduleId) {
        try {
            Schedule schedule = jdbcTemplate.queryForObject(sql.findById(), rowMapper, scheduleId);
            return Optional.ofNullable(schedule);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Schedule> findAll() {
        try {
            return jdbcTemplate.query(sql.findAll(), rowMapper);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Schedule> findAllByPage(Integer page, Integer size) {
        try {
            int limit = size;
            int offset = (page - 1) * size;

            return jdbcTemplate.query(sql.findByPage(), rowMapper, limit, offset);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Schedule> findAllByFilter(String updatedTime, Long managerId) {
        try {
            return jdbcTemplate.query(sql.findAllByFilter(updatedTime, managerId), rowMapper);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }
    }
}
