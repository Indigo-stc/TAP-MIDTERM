package com.ista.examenpracticom5b.controller;

import com.ista.examenpracticom5b.model.Producto;
import com.ista.examenpracticom5b.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto/")
public class ProductoCtrl {

    @Autowired
    private IService<Producto, Integer> service;

    @GetMapping("listar")
    public ResponseEntity<List<Producto>> getAll() {
        try {
            return new ResponseEntity<>(service.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Producto> getOne(@PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("crear")
    public ResponseEntity<?> save(@RequestBody Producto t){
        try {
            if (t.getPrecion() > 0
            && t.getDescripcion().length() <= 100
            && t.getCantidad() > 0) {
                //return new ResponseEntity(service.save(t), HttpStatus.CREATED);
                service.save(t);
                return ResponseEntity.status(HttpStatus.CREATED).body(t.toString());
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Descripcion debe tener maximo 100 caracteres, valores deben ser mayores a 0");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar al cancion por que el ya esta en otra entidad realacinada");
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Producto t, @PathVariable("id") Integer id){
        Producto current = service.findById(id);
        if (current == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                if (t.getPrecion() > 0
                        && t.getDescripcion().length() <= 100
                        && t.getCantidad() > 0) {
                    current.setDescripcion(t.getDescripcion());
                    current.setCantidad(t.getCantidad());
                    current.setPrecion(t.getPrecion());
                    return new ResponseEntity<>(service.save(current), HttpStatus.CREATED);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar al cancion por que el ya esta en otra entidad realacinada");
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}