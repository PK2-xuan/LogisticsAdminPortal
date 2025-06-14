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


/*
 *     @Override
    public boolean validarCredenciales(String correo, String contraseña) {
        boolean esValido = false;
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_LOGIN_ADMINISTRADOR");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

            query.setParameter(1, correo);
            query.setParameter(2, contraseña);

            List<Object[]> results = query.getResultList();
            if (!results.isEmpty()) {
                esValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error validarCredenciales: " + e.getMessage());
        }
        return esValido;
    }
    
 *     @Override
    public boolean insertarAdministrador(Administrador administrador) {
        boolean respuesta = false;
        try {
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("SP_INSERT_ADMINISTRADOR");
            spQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);

            spQuery.setParameter(1, administrador.getNombre());
            spQuery.setParameter(2, administrador.getCorreo());
            spQuery.setParameter(3, administrador.getContraseña());

            spQuery.execute();
            respuesta = true;
        } catch (Exception e) {
            System.out.println("Error insertarAdministrador: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public boolean updateAdministrador(Administrador administrador, int id) {
        boolean respuesta = false;
        try {
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("SP_UPDATE_ADMINISTRADOR");
            spQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
            spQuery.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);

            spQuery.setParameter(1, administrador.getNombre());
            spQuery.setParameter(2, administrador.getCorreo());
            spQuery.setParameter(3, administrador.getContraseña());
            spQuery.setParameter(4, id);

            spQuery.execute();
            respuesta = true;
        } catch (Exception e) {
            System.out.println("Error updateAdministrador: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public boolean deleteAdministrador(int id) {
        boolean respuesta = false;
        try {
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("SP_DELETE_ADMINISTRADOR");
            spQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            spQuery.setParameter(1, id);
            spQuery.execute();
            respuesta = true;
        } catch (Exception e) {
            System.out.println("Error deleteAdministrador: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<Administrador> obtenerAdministradores() {
        List<Administrador> listaAdministradores = new ArrayList<>();
        try {
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("SP_LISTA_ADMINISTRADORES");
            spQuery.execute();

            List<Object[]> results = spQuery.getResultList();
            for (Object[] obj : results) {
                Administrador administrador = new Administrador();
                administrador.setIdAdministrador((Integer) obj[0]);
                administrador.setNombre((String) obj[1]);
                administrador.setCorreo((String) obj[2]);
                administrador.setContraseña((String) obj[3]);
                listaAdministradores.add(administrador);
            }
        } catch (Exception e) {
            System.out.println("Error obtenerAdministradores: " + e.getMessage());
        }
        return listaAdministradores;
    }

    @Override
    public Administrador obtenerAdministradorPorId(int id) {
        Administrador administrador = new Administrador();
        try {
            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("SP_LISTA_ADMINISTRADOR_ID");
            spQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            spQuery.setParameter(1, id);

            List<Object[]> results = spQuery.getResultList();
            for (Object[] obj : results) {
                administrador.setIdAdministrador((Integer) obj[0]);
                administrador.setNombre((String) obj[1]);
                administrador.setCorreo((String) obj[2]);
                administrador.setContraseña((String) obj[3]);
            }
        } catch (Exception e) {
            System.out.println("Error obtenerAdministradorPorId: " + e.getMessage());
        }
        return administrador;
    }

 */



