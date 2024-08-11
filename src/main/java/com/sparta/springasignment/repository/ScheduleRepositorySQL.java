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
        String query = "select * from schedules as s join (select schedule_id from schedules limit ? offset ?) as t on s.schedule_id = t.schedule_id";
        return query;
    }



    public String delete(){
        return "DELETE FROM schedules WHERE schedule_id = ?";
    }
}
