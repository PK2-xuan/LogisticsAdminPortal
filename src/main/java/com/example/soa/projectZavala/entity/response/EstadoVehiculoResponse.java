package com.example.soa.projectZavala.entity.response;

import java.util.List;

import com.example.soa.projectZavala.entity.EstadoVehiculo;

public class EstadoVehiculoResponse {

    private List<EstadoVehiculo> listaEstadosVehiculo;

    public List<EstadoVehiculo> getListaEstadosVehiculo() {
        return listaEstadosVehiculo;
    }

    public void setListaEstadosVehiculo(List<EstadoVehiculo> listaEstadosVehiculo) {
        this.listaEstadosVehiculo = listaEstadosVehiculo;
    }

    public List<EstadoVehiculo> obtenerListaEstadosVehiculo() {
        return listaEstadosVehiculo;
    }
}
