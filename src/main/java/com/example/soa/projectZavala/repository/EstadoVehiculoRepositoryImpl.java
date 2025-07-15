package com.example.soa.projectZavala.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.soa.projectZavala.dto.ReporteUtilizacionDTO;
import com.example.soa.projectZavala.entity.EstadoVehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.*;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class EstadoVehiculoRepositoryImpl implements EstadoVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean insertarEstadoVehiculo(EstadoVehiculo estado) {
		boolean respuesta = false;
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERT_ESTADO_VEHICULO");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // nombre
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // descripcion
			query.setParameter(1, estado.getNombre());
			query.setParameter(2, estado.getDescripcion());
			query.execute();
			respuesta = true;
		} catch (Exception e) {
			System.out.println("Error al insertar EstadoVehiculo: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public boolean updateEstadoVehiculo(EstadoVehiculo estado, int id) {
		boolean respuesta = false;
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_UPDATE_ESTADO_VEHICULO");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // nombre
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // descripcion
			query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); // id
			query.setParameter(1, estado.getNombre());
			query.setParameter(2, estado.getDescripcion());
			query.setParameter(3, id);
			query.execute();
			respuesta = true;
		} catch (Exception e) {
			System.out.println("Error al actualizar EstadoVehiculo: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public boolean deleteEstadoVehiculo(int id) {
		boolean respuesta = false;
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_DELETE_ESTADO_VEHICULO");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.setParameter(1, id);
			query.execute();
			respuesta = true;
		} catch (Exception e) {
			System.out.println("Error al eliminar EstadoVehiculo: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public List<EstadoVehiculo> obtenerEstadosVehiculo() {
		List<EstadoVehiculo> listaEstados = new ArrayList<>();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_LISTAR_ESTADOVEHICULO");
			query.execute();
			List<Object[]> results = query.getResultList();
			for (Object[] obj : results) {
				EstadoVehiculo estado = new EstadoVehiculo();
				estado.setIdEstado((Integer) obj[0]);
				estado.setNombre((String) obj[1]);
				estado.setDescripcion((String) obj[2]);
				listaEstados.add(estado);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener lista de EstadosVehiculo: " + e.getMessage());
		}
		return listaEstados;
	}

	@Override
	public EstadoVehiculo obtenerEstadoVehiculoPorId(int id) {
		EstadoVehiculo estado = new EstadoVehiculo();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_OBTENER_ESTADO_VEHICULO_POR_ID");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.setParameter(1, id);
			query.execute();
			List<Object[]> results = query.getResultList();
			if (!results.isEmpty()) {
				Object[] obj = results.get(0);
				estado.setIdEstado((Integer) obj[0]);
				estado.setNombre((String) obj[1]);
				estado.setDescripcion((String) obj[2]);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener EstadoVehiculo por id: " + e.getMessage());
		}
		return estado;
	}

	// SERVICIO

	@Override
	public List<ReporteUtilizacionDTO> obtenerReporteUtilizacionVehiculos() {
		List<ReporteUtilizacionDTO> lista = new ArrayList<>();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_REPORTE_UTILIZACION_VEHICULOS");
			query.execute();
			List<Object[]> results = query.getResultList();

			for (Object[] obj : results) {
				ReporteUtilizacionDTO dto = new ReporteUtilizacionDTO();
				dto.setIdVehiculo((Integer) obj[0]);
				dto.setPlaca((String) obj[1]);
				dto.setIdServicio((Integer) obj[2]);
				dto.setNombreServicio((String) obj[3]);
				dto.setVecesAsignado(((Number) obj[4]).intValue());
				dto.setFechasAsignacion((String) obj[5]);
				lista.add(dto);
			}
		} catch (Exception e) {
			System.err.println("Error al obtener reporte de utilización: " + e.getMessage());
		}
		return lista;
	}

	@Override
	public void actualizarEstadoVehiculosPorMantenimiento() {
		try {
            StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("SP_ACTUALIZAR_ESTADO_MANTENIMIENTO");
            query.execute();
        } catch (Exception e) {
            System.err.println("Error al ejecutar SP_ACTUALIZAR_ESTADO_MANTENIMIENTO: " + e.getMessage());
        }
    }

}
