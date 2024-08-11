package com.sparta.springasignment.repository;

import org.springframework.stereotype.Component;

@Component
public class ScheduleRepositorySQL {

    public String save(){
        return "INSERT INTO schedules (manager_id, password, contents, created_time, updated_time) VALUES(?,?,?,?,?)";
    }

    public String update(){
        return "UPDATE schedules SET manager_id = ?, password = ?, contents = ?, created_time = ?, updated_time = ? where schedule_id = ?";
    }

    public String findById(){
        return "SELECT * FROM schedules WHERE schedule_id = ?";
    }

    public String findAll(){
        return "SELECT * FROM schedules";
    }

    public String findByPage(){
        return "SELECT * FROM schedules LIMIT ? OFFSET ?";
    }

    public String delete(){
        return "DELETE FROM managers WHERE manager_id = ?";
    }
}
