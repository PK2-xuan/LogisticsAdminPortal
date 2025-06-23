package com.example.soa.projectZavala.entity;



public class Consulta {
	
    private String placa;
    private String modelo;
    private String marca;
    private int capacidadEquipaje;
    private int servicioId;

    // Getters y Setters
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

    public int getCapacidadEquipaje() {
        return capacidadEquipaje;
    }

    public void setCapacidadEquipaje(int capacidadEquipaje) {
        this.capacidadEquipaje = capacidadEquipaje;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }
}
