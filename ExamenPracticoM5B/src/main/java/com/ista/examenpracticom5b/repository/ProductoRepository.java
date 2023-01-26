package com.ista.examenpracticom5b.repository;

import com.ista.examenpracticom5b.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
