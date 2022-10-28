package com.robot.service;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface GenericService<T extends Object> {

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    void delete(Long id);

    void deleteAll(List<T> entities);

    Long countAll();

    T find(Long id);

    List<T> findAll();
}
