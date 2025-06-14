package com.example.soa.projectZavala.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.soa.projectZavala.entity.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class VehiculoRepositoryImpl implements VehiculoRepository{

    @PersistenceContext
    private EntityManager entityManager;	

	@Override
	public boolean insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateVehiculo(Vehiculo vehiculo, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteVehiculo(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Vehiculo> obtenerVehiculos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo obtenerVehiculoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
