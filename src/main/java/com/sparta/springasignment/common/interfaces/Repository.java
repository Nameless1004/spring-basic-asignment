package com.sparta.springasignment.common.interfaces;

import java.util.List;
import java.util.Optional;

public interface Repository<E extends Entity, Key> {

    Key save(E entity);

    void update(E entity);

    void delete(E entity);

    Optional<E> findById(Key id);

    List<E> findAll();
}
