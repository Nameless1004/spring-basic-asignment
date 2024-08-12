package com.sparta.springasignment.repository;

import com.sparta.springasignment.entity.Manager;
import com.sparta.springasignment.repository.interfaces.ManagerRepository;
import com.sparta.springasignment.repository.rowmapper.ManagerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepository {
    
  private final JdbcTemplate jdbcTemplate;
  private final ManagerRepositorySQL sql;
  private final ManagerRowMapper managerRowMapper;

  private final KeyHolder keyHolder = new GeneratedKeyHolder();

  @Override
  public Long save(Manager manager) {
    if (manager.getId() == null) {
      jdbcTemplate.update(con -> {
        PreparedStatement preparedStatement = con.prepareStatement(sql.save(),
            Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, manager.getName());
        preparedStatement.setString(2, manager.getEmail());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(manager.getCreatedTime()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(manager.getUpdatedTime()));
        return preparedStatement;
      }, keyHolder);
    }
    return keyHolder.getKey().longValue();
  }

  @Override
  public void update(Manager manager) {
    jdbcTemplate.update(sql.update(), manager.getName(), manager.getEmail(),
        manager.getCreatedTime(), manager.getUpdatedTime(), manager.getId());
  }

  @Override
  public void delete(Manager manager) {
    jdbcTemplate.update(sql.delete(), manager.getId());
  }

  @Override
  public Optional<Manager> findById(Long id) {
    try {
      Manager manager = jdbcTemplate.queryForObject(sql.findById(), managerRowMapper, id);
      return Optional.ofNullable(manager);
    } catch (DataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Manager> findAll() {
    try {
      List<Manager> managers = jdbcTemplate.query(sql.findAll(), managerRowMapper);
      return managers;
    } catch (DataAccessException e) {
      return new ArrayList<>();
    }
  }
}
