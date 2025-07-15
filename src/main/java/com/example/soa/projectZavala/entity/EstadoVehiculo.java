package com.example.soa.projectZavala.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EstadoVehiculo")
public class EstadoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    // Getters y Setters

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
