package com.example.soa.projectZavala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soa.projectZavala.entity.Vehiculo;
import com.example.soa.projectZavala.entity.response.VehiculoResponse;
import com.example.soa.projectZavala.repository.VehiculoRepository;

@Service
@Transactional
public class VehiculoService {

	@Autowired
    private VehiculoRepository vehiculoRepository;

    public boolean delete(int id) {
        return vehiculoRepository.deleteVehiculo(id);
    }

    public boolean insert(Vehiculo vehiculo) {
        return vehiculoRepository.insertarVehiculo(vehiculo);
    }

    public boolean update(Vehiculo vehiculo, int id) {
        return vehiculoRepository.updateVehiculo(vehiculo, id);
    }

    public VehiculoResponse getList() {
        VehiculoResponse response = new VehiculoResponse();
        List<Vehiculo> lista = vehiculoRepository.obtenerVehiculos();
        response.setListaVehiculos(lista);
        return response;
    }

    public Vehiculo getById(int id) {
        return vehiculoRepository.obtenerVehiculoPorId(id);
    }
}
