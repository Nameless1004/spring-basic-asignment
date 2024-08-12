package com.sparta.springasignment.schedule.repository;

import java.text.MessageFormat;
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

    public String findAllByFilter(String updatedTime, Long managerId) {
        String sql = "select * from schedules";

        if (updatedTime != null && managerId == null) {
            sql += MessageFormat.format(
                " where DATE_FORMAT(updated_time, ''%Y-%m-%d'') in (''{0}'')",
                updatedTime);
        } else if (updatedTime == null && managerId != null) {
            sql += " where manager_id = " + managerId;
        } else if (updatedTime != null && managerId != null) {
            sql +=
                MessageFormat.format(" where DATE_FORMAT(updated_time, ''%Y-%m-%d'') in (''{0}'')",
                    updatedTime) + " and manager_id = " + managerId;
        }

        sql += " order by updated_time desc";
        return sql;
    }


    public String delete(){
        return "DELETE FROM schedules WHERE schedule_id = ?";
    }
}
