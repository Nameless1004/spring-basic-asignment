package com.sparta.springasignment.repository.interfaces;

import com.sparta.springasignment.entity.Entity;
import com.sparta.springasignment.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends Repository<Schedule, Long> {
    List<Schedule> findAllByQuery(String query);
    List<Schedule> findAllByPage(Integer page, Integer size);
}
