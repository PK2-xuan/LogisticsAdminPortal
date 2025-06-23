package com.example.soa.projectZavala.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.soa.projectZavala.entity.Consulta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ConsultaRepositoryImpl implements ConsultaRepository {
	
    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Consulta> obtenerVehiculosMantenimientoPrevio(Date fechaLimite, int servicioId) {
		List<Consulta> resultados = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery("CALL ObtenerVehiculosMantenimientoPrevio(?, ?)");
            query.setParameter(1, fechaLimite);
            query.setParameter(2, servicioId);

            List<Object[]> rows = query.getResultList();
            for (Object[] row : rows) {
            	Consulta consulta = new Consulta();
                consulta.setPlaca((String) row[0]);
                consulta.setModelo((String) row[1]);
                consulta.setMarca((String) row[2]);
                consulta.setCapacidadEquipaje(((Number) row[3]).intValue());
                consulta.setServicioId(((Number) row[4]).intValue());
                resultados.add(consulta);
            }

        } catch (Exception e) {
            System.err.println("Error al ejecutar SP: " + e.getMessage());
        }
        return resultados;
	}

}
