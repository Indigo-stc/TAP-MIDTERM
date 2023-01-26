package com.ista.examenpracticom5b.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;
    @Nonnull
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @Nonnull
    @Column(name = "precion")
    private Double precion;
    @Nonnull
    @Column(name = "cantidad")
    private Integer cantidad;

    public double calcularCosto() {
        return cantidad*precion;
    }

    public double calcularIva() {
        return cantidad*precion*0.12;
    }

    @Override
    public String toString() {
        return "Datos relevantes --> " +
                "iva = " + calcularIva() +
                ", costoTotal = " + calcularCosto();
    }

}
