package com.example.soa.projectZavala.repository;

import java.util.List;

import com.example.soa.projectZavala.entity.Vehiculo;

public interface VehiculoRepository {

    boolean insertarVehiculo(Vehiculo vehiculo);

    boolean updateVehiculo(Vehiculo vehiculo, int id);

    boolean deleteVehiculo(int id);

    List<Vehiculo> obtenerVehiculos();

    Vehiculo obtenerVehiculoPorId(int id);
}