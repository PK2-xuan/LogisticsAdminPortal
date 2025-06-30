package com.example.soa.projectZavala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soa.projectZavala.entity.Mantenimiento;
import com.example.soa.projectZavala.entity.response.MantenimientoResponse;
import com.example.soa.projectZavala.repository.MantenimientoRepository;

@Service
@Transactional
public class MantenimientoService {

	@Autowired
	private MantenimientoRepository mantenimientoRepository;

	public boolean delete(int id) {
		return mantenimientoRepository.deleteMantenimiento(id);
	}

	public boolean insert(Mantenimiento mantenimiento) {
		return mantenimientoRepository.insertarMantenimiento(mantenimiento);
	}

	public boolean update(Mantenimiento mantenimiento, int id) {
		return mantenimientoRepository.updateMantenimiento(mantenimiento, id);
	}

	public MantenimientoResponse getList() {
		MantenimientoResponse response = new MantenimientoResponse();
		List<Mantenimiento> lista = mantenimientoRepository.obtenerMantenimientos();
		response.setListaMantenimientos(lista);
		return response;
	}

	public Mantenimiento getById(int id) {
		return mantenimientoRepository.obtenerMantenimientoPorId(id);
	}
}
