package com.sparta.springasignment.manager.repository;

import org.springframework.stereotype.Component;

@Component
public class ManagerRepositorySQL {

    public String save() {
        return "INSERT INTO managers (name, email, created_time, updated_time) VALUES(?,?,?,?)";
    }

    public String update() {
        return "UPDATE managers SET name = ?, email = ?, created_time = ?, updated_time = ? where manager_id = ?";
    }

    public String findById() {
        return "SELECT * FROM managers WHERE manager_id = ?";
    }

    public String findAll() {
        return "SELECT * FROM managers";
    }

    public String delete() {
        return "DELETE FROM managers WHERE manager_id = ?";
    }
}
