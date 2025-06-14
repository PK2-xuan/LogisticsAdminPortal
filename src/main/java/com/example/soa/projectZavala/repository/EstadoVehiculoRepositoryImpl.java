package com.example.soa.projectZavala.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.soa.projectZavala.entity.EstadoVehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.*;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class EstadoVehiculoRepositoryImpl implements EstadoVehiculoRepository{
	
    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public boolean insertarEstadoVehiculo(EstadoVehiculo estado) {
		 boolean respuesta = false;
	        try {
	            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERT_ESTADO_VEHICULO");
	            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	            query.setParameter(1, estado.getDescripcion());
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
	            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
	            query.setParameter(1, estado.getDescripcion());
	            query.setParameter(2, id);
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
                estado.setDescripcion((String) obj[1]);
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
	            List<Object[]> results = query.getResultList();
	            if (!results.isEmpty()) {
	                Object[] obj = results.get(0);
	                estado.setIdEstado((Integer) obj[0]);
	                estado.setDescripcion((String) obj[1]);
	            }
	        } catch (Exception e) {
	            System.out.println("Error al obtener EstadoVehiculo por id: " + e.getMessage());
	        }
	        return estado;
	}

}
