package com.lga.tennisscoreboard.repository;

import com.lga.tennisscoreboard.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DaoCrud<K extends Serializable, E extends BaseEntity<K>> {

    Optional<E> findById(K entityId);

    List<E> findAll();

    E save(E entity);

    void delete (K entityId);

    void update(E entity);
}
