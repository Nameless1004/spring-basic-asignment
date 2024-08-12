package com.sparta.springasignment.schedule.repository;

import com.sparta.springasignment.common.interfaces.Repository;
import com.sparta.springasignment.schedule.entity.Schedule;
import java.util.List;

public interface ScheduleRepository extends Repository<Schedule, Long> {
    
    List<Schedule> findAllByPage(Integer page, Integer size);

    List<Schedule> findAllByFilter(String updatedTime, Long managerId);
}
