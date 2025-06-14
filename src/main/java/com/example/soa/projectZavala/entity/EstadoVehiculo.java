package com.example.soa.projectZavala.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "EstadoVehiculo")
public class EstadoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(nullable = false, length = 50)
    private String descripcion;

    // Constructor vacío
    public EstadoVehiculo() {
    }

    // Getters y Setters
    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}