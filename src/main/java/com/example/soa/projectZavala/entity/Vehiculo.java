package com.example.soa.projectZavala.entity;

import jakarta.persistence.*;
//import lombok.*;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @Column(length = 50)
    private String modelo;

    @Column(length = 50)
    private String marca;

    @Column(name = "capacidad_equipaje")
    private Integer capacidadEquipaje;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoVehiculo estado;

    // Constructor vacío
    public Vehiculo() {
    }

    // Getters y Setters
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCapacidadEquipaje() {
        return capacidadEquipaje;
    }

    public void setCapacidadEquipaje(Integer capacidadEquipaje) {
        this.capacidadEquipaje = capacidadEquipaje;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }
}