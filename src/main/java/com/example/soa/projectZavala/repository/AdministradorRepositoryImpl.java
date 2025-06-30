package com.example.soa.projectZavala.repository;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

//import com.example.soa.projectZavala.entity.Administrador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.*;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class AdministradorRepositoryImpl implements AdministradorRepository{


    @PersistenceContext
    private EntityManager entityManager;   
    
    @Override
    public String obtenerNombreSiCredencialesSonValidas(String correo, String contraseña) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_LOGIN_ADMINISTRADOR");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

            query.setParameter(1, correo);
            query.setParameter(2, contraseña);

            List<Object> result = query.getResultList();

            if (!result.isEmpty()) {
                return result.get(0).toString(); // devuelve el nombre
            }
        } catch (Exception e) {
            System.out.println("Error obtenerNombre: " + e.getMessage());
        }
        return null;
    }
}