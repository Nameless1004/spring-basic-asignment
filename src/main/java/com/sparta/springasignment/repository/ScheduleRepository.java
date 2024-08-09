package com.sparta.springasignment.repository;

import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private KeyHolder keyHolder =  new GeneratedKeyHolder();

    public Long save(Schedule schedule){
        String sql = "INSERT INTO schedules (manager_id, password, contents, created_time, updated_time) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, schedule.getManagerId());
            preparedStatement.setString(2, schedule.getPassword());
            preparedStatement.setString(3, schedule.getContents());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(schedule.getCreatedTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getUpdatedTime()));
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Long save(Manager manager){
        String sql = "INSERT INTO managers (name, email, created_time, updated_time) VALUES(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, manager.getName());
            preparedStatement.setString(2, manager.getEmail());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(manager.getCreatedTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(manager.getUpdatedTime()));
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Optional<Schedule> findScheduleById(Long scheduleId){
        String sql = "SELECT * FROM schedules WHERE schedule_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new RowMapper<Optional<Schedule>>() {
                @Override
                public Optional<Schedule> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Schedule schedule = new Schedule(
                            rs.getLong("schedule_id"),
                            rs.getLong("manager_id"),
                            rs.getString("password"),
                            rs.getString("contents"),
                            rs.getTimestamp("created_time").toLocalDateTime(),
                            rs.getTimestamp("updated_time").toLocalDateTime()
                    );
                    return Optional.of(schedule);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Manager> createSchedule(Long managerId){
        return Optional.empty();
    }
}
