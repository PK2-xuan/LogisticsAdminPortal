package com.example.soa.projectZavala.entity.response;

import java.util.List;

import com.example.soa.projectZavala.entity.Vehiculo;

public class VehiculoResponse {

    private List<Vehiculo> listaVehiculos;

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public List<Vehiculo> obtenerListaVehiculos() {
        return listaVehiculos;
    }
}
