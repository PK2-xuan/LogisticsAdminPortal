package com.example.soa.projectZavala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soa.projectZavala.entity.EstadoVehiculo;
import com.example.soa.projectZavala.entity.response.EstadoVehiculoResponse;
import com.example.soa.projectZavala.repository.EstadoVehiculoRepository;

@Service
@Transactional
public class EstadoVehiculoService {

    @Autowired
    private EstadoVehiculoRepository estadoVehiculoRepository;

    public boolean delete(int id) {
        return estadoVehiculoRepository.deleteEstadoVehiculo(id);
    }

    public boolean insert(EstadoVehiculo estado) {
        return estadoVehiculoRepository.insertarEstadoVehiculo(estado);
    }

    public boolean update(EstadoVehiculo estado, int id) {
        return estadoVehiculoRepository.updateEstadoVehiculo(estado, id);
    }

    public EstadoVehiculoResponse getList() {
        EstadoVehiculoResponse response = new EstadoVehiculoResponse();
        List<EstadoVehiculo> lista = estadoVehiculoRepository.obtenerEstadosVehiculo();
        response.setListaEstadosVehiculo(lista);
        return response;
    }

    public EstadoVehiculo getById(int id) {
        return estadoVehiculoRepository.obtenerEstadoVehiculoPorId(id);
    }
}
