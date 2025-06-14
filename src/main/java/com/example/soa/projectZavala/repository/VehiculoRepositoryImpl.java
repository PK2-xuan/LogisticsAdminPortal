package com.example.soa.projectZavala.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.soa.projectZavala.entity.EstadoVehiculo;
import com.example.soa.projectZavala.entity.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class VehiculoRepositoryImpl implements VehiculoRepository{

    @PersistenceContext
    private EntityManager entityManager;	

	@Override
	public boolean insertarVehiculo(Vehiculo vehiculo) {
		 boolean respuesta = false;
	        try {
	            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERT_VEHICULO");
	            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // placa
	            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // modelo
	            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // marca
	            query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // capacidadEquipaje
	            query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN); // estado_id

	            query.setParameter(1, vehiculo.getPlaca());
	            query.setParameter(2, vehiculo.getModelo());
	            query.setParameter(3, vehiculo.getMarca());
	            query.setParameter(4, vehiculo.getCapacidadEquipaje());
	            query.setParameter(5, vehiculo.getEstado().getIdEstado());

	            query.execute();
	            respuesta = true;
	        } catch (Exception e) {
	            System.out.println("Error al insertar Vehiculo: " + e.getMessage());
	        }
	        return respuesta;
	}

	@Override
	public boolean updateVehiculo(Vehiculo vehiculo, int id) {
		  boolean respuesta = false;
	        try {
	            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_UPDATE_VEHICULO");
	            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
	            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
	            query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
	            query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN); // estado_id
	            query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN); // id del vehículo

	            query.setParameter(1, vehiculo.getPlaca());
	            query.setParameter(2, vehiculo.getModelo());
	            query.setParameter(3, vehiculo.getMarca());
	            query.setParameter(4, vehiculo.getCapacidadEquipaje());
	            query.setParameter(5, vehiculo.getEstado().getIdEstado());
	            query.setParameter(6, id);

	            query.execute();
	            respuesta = true;
	        } catch (Exception e) {
	            System.out.println("Error al actualizar Vehiculo: " + e.getMessage());
	        }
	        return respuesta;
	}

	@Override
	public boolean deleteVehiculo(int id) {
		 boolean respuesta = false;
	        try {
	            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_DELETE_VEHICULO");
	            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
	            query.setParameter(1, id);
	            query.execute();
	            respuesta = true;
	        } catch (Exception e) {
	            System.out.println("Error al eliminar Vehiculo: " + e.getMessage());
	        }
	        return respuesta;
	}

	@Override
	public List<Vehiculo> obtenerVehiculos() {
		  List<Vehiculo> listaVehiculos = new ArrayList<>();
	        try {
	            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_LISTAR_VEHICULOS");
	            query.execute();

	            List<Object[]> results = query.getResultList();
	            for (Object[] obj : results) {
	                Vehiculo vehiculo = new Vehiculo();
	                EstadoVehiculo estado = new EstadoVehiculo();

	                vehiculo.setIdVehiculo((Integer) obj[0]);
	                vehiculo.setPlaca((String) obj[1]);
	                vehiculo.setModelo((String) obj[2]);
	                vehiculo.setMarca((String) obj[3]);
	                vehiculo.setCapacidadEquipaje((Integer) obj[4]);

	                estado.setIdEstado((Integer) obj[5]);
	                estado.setDescripcion((String) obj[6]);
	                vehiculo.setEstado(estado);

	                listaVehiculos.add(vehiculo);
	            }
	        } catch (Exception e) {
	            System.out.println("Error al listar Vehiculos: " + e.getMessage());
	        }
	        return listaVehiculos;
	}

	@Override
	public Vehiculo obtenerVehiculoPorId(int id) {
		 Vehiculo vehiculo = new Vehiculo();
	        try {
	            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_OBTENER_VEHICULO_POR_ID");
	            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
	            query.setParameter(1, id);

	            List<Object[]> results = query.getResultList();
	            if (!results.isEmpty()) {
	                Object[] obj = results.get(0);
	                EstadoVehiculo estado = new EstadoVehiculo();

	                vehiculo.setIdVehiculo((Integer) obj[0]);
	                vehiculo.setPlaca((String) obj[1]);
	                vehiculo.setModelo((String) obj[2]);
	                vehiculo.setMarca((String) obj[3]);
	                vehiculo.setCapacidadEquipaje((Integer) obj[4]);

	                estado.setIdEstado((Integer) obj[5]);
	                estado.setDescripcion((String) obj[6]);
	                vehiculo.setEstado(estado);
	            }
	        } catch (Exception e) {
	            System.out.println("Error al obtener Vehiculo por ID: " + e.getMessage());
	        }
	        return vehiculo;
	}

}
