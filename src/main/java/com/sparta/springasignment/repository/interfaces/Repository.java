package com.sparta.springasignment.repository.interfaces;

import com.sparta.springasignment.entity.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface Repository <E extends Entity, Key>{
    Key save(E entity);
    void update(E entity);
    void delete(E entity);
    Optional<E> findById(Key id);
    List<E> findAll();
}
