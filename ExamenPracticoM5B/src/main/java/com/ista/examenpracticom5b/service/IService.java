package com.ista.examenpracticom5b.service;

import java.util.List;

public interface IService<T, ID> {

    T save(T entity);

    T findById(ID id);

    List<T> findByAll();

    void delete(ID id);

}
