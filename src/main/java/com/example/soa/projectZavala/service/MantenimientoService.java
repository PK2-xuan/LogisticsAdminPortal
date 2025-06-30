package com.example.soa.projectZavala.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soa.projectZavala.entity.Administrador;
import com.example.soa.projectZavala.entity.Mantenimiento;
import com.example.soa.projectZavala.entity.Vehiculo;
import com.example.soa.projectZavala.entity.response.MantenimientoResponse;
import com.example.soa.projectZavala.entity.response.MantenimientoResponseDTO;
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

	public List<MantenimientoResponseDTO> mapToDtoList(List<Mantenimiento> lista) {
		return lista.stream().map(m -> {
			MantenimientoResponseDTO dto = new MantenimientoResponseDTO();
			dto.setIdMantenimiento(m.getIdMantenimiento());
			dto.setVehiculoId(m.getVehiculo().getIdVehiculo());
			dto.setFechaProgramada(m.getFechaProgramada());
			dto.setRealizado(m.getRealizado());
			dto.setDescripcion(m.getDescripcion());
			dto.setEstado(m.getEstado());
			dto.setAdministradorId(m.getAdministrador().getIdAdministrador());
			return dto;
		}).toList();
	}

	// SERVICIO
	public List<Mantenimiento> getHistorialPorVehiculo(int vehiculoId) {
		return mantenimientoRepository.obtenerMantenimientosPorVehiculoId(vehiculoId);
	}

	public boolean programarProximoMantenimiento(int vehiculoId, int adminId) {

	    return null;
	}

}
