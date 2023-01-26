package com.ista.examenpracticom5b.service;

import com.ista.examenpracticom5b.model.Producto;
import com.ista.examenpracticom5b.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IService<Producto, Integer>  {

    @Autowired
    ProductoRepository repo;
    @Override
    public Producto save(Producto entity) {
        return repo.save(entity);
    }

    @Override
    public Producto findById(Integer integer) {
        return repo.findById(integer).orElse(null);
    }

    @Override
    public List<Producto> findByAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer integer) {
        repo.deleteById(integer);
    }
}
