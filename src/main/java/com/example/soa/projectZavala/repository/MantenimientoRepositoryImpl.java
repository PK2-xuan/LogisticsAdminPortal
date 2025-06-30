package com.example.soa.projectZavala.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.soa.projectZavala.entity.Administrador;
import com.example.soa.projectZavala.entity.Mantenimiento;
import com.example.soa.projectZavala.entity.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class MantenimientoRepositoryImpl implements MantenimientoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean insertarMantenimiento(Mantenimiento mantenimiento) {
		boolean respuesta = false;
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERT_MANTENIMIENTO");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN); // vehiculo_id
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // fecha_programada
			query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // realizado
			query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // descripcion
			query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // estado
			query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN); // administrador_id

			query.setParameter(1, mantenimiento.getVehiculo().getIdVehiculo());
			query.setParameter(2, mantenimiento.getFechaProgramada());
			query.setParameter(3, mantenimiento.getRealizado());
			query.setParameter(4, mantenimiento.getDescripcion());
			query.setParameter(5, mantenimiento.getEstado());
			query.setParameter(6, mantenimiento.getAdministrador().getIdAdministrador());

			query.execute();
			respuesta = true;
		} catch (Exception e) {
			System.out.println("Error al insertar Mantenimiento: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public boolean updateMantenimiento(Mantenimiento mantenimiento, int id) {
		boolean respuesta = false;
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_UPDATE_MANTENIMIENTO");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN); // id_mantenimiento
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN); // vehiculo_id
			query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // fecha_programada
			query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // realizado
			query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // descripcion
			query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // estado
			query.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN); // administrador_id

			query.setParameter(1, id);
			query.setParameter(2, mantenimiento.getVehiculo().getIdVehiculo());
			query.setParameter(3, mantenimiento.getFechaProgramada());
			query.setParameter(4, mantenimiento.getRealizado());
			query.setParameter(5, mantenimiento.getDescripcion());
			query.setParameter(6, mantenimiento.getEstado());
			query.setParameter(7, mantenimiento.getAdministrador().getIdAdministrador());

			query.execute();
			respuesta = true;
		} catch (Exception e) {
			System.out.println("Error al actualizar Mantenimiento: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public boolean deleteMantenimiento(int id) {
		boolean respuesta = false;
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_DELETE_MANTENIMIENTO");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.setParameter(1, id);
			query.execute();
			respuesta = true;
		} catch (Exception e) {
			System.out.println("Error al eliminar Mantenimiento: " + e.getMessage());
		}
		return respuesta;
	}

	@Override
	public List<Mantenimiento> obtenerMantenimientos() {
		List<Mantenimiento> lista = new ArrayList<>();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_LISTAR_MANTENIMIENTOS");
			query.execute();

			List<Object[]> results = query.getResultList();
			for (Object[] obj : results) {
				Mantenimiento m = new Mantenimiento();
				Vehiculo v = new Vehiculo();
				Administrador a = new Administrador();

				m.setIdMantenimiento((Integer) obj[0]);
				v.setIdVehiculo((Integer) obj[1]);
				m.setFechaProgramada((String) obj[2]);
				m.setRealizado((String) obj[3]);
				m.setDescripcion((String) obj[4]);
				m.setEstado((String) obj[5]);
				a.setIdAdministrador((Integer) obj[6]);

				m.setVehiculo(v);
				m.setAdministrador(a);

				lista.add(m);
			}
		} catch (Exception e) {
			System.out.println("Error al listar Mantenimientos: " + e.getMessage());
		}
		return lista;
	}

	@Override
	public Mantenimiento obtenerMantenimientoPorId(int id) {
		Mantenimiento m = new Mantenimiento();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_OBTENER_MANTENIMIENTO_POR_ID");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.setParameter(1, id);

			List<Object[]> results = query.getResultList();
			if (!results.isEmpty()) {
				Object[] obj = results.get(0);
				Vehiculo v = new Vehiculo();
				Administrador a = new Administrador();

				m.setIdMantenimiento((Integer) obj[0]);
				v.setIdVehiculo((Integer) obj[1]);
				m.setFechaProgramada((String) obj[2]);
				m.setRealizado((String) obj[3]);
				m.setDescripcion((String) obj[4]);
				m.setEstado((String) obj[5]);
				a.setIdAdministrador((Integer) obj[6]);

				m.setVehiculo(v);
				m.setAdministrador(a);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener Mantenimiento por ID: " + e.getMessage());
		}
		return m;
	}

	// SERVICIOS
	@Override
	public List<Mantenimiento> obtenerMantenimientosPorVehiculoId(int vehiculoId) {
		List<Mantenimiento> lista = new ArrayList<>();
	    try {
	        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_HISTORIAL_MANTENIMIENTOS_POR_VEHICULO");
	        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
	        query.setParameter(1, vehiculoId);
	        query.execute();

	        List<Object[]> results = query.getResultList();
	        for (Object[] obj : results) {
	            Mantenimiento m = new Mantenimiento();
	            Vehiculo v = new Vehiculo();
	            Administrador a = new Administrador();

	            m.setIdMantenimiento((Integer) obj[0]);
	            v.setIdVehiculo((Integer) obj[1]);
	            m.setFechaProgramada((String) obj[2]);
	            m.setRealizado((String) obj[3]);
	            m.setDescripcion((String) obj[4]);
	            m.setEstado((String) obj[5]);
	            a.setIdAdministrador((Integer) obj[6]);

	            m.setVehiculo(v);
	            m.setAdministrador(a);
	            lista.add(m);
	        }
	    } catch (Exception e) {
	        System.out.println("Error al obtener historial de mantenimientos: " + e.getMessage());
	    }
	    return lista;
	}

	@Override
	public String obtenerUltimaFechaMantenimiento(int vehiculoId) {
	    try {
	        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_OBTENER_ULTIMA_FECHA_MANTENIMIENTO");
	        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
	        query.setParameter(1, vehiculoId);
	        query.execute();
	        return (String) query.getOutputParameterValue(2);
	    } catch (Exception e) {
	        System.out.println("Error al obtener última fecha de mantenimiento: " + e.getMessage());
	    }
	    return null;
	}	
}
