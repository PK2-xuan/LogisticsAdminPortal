package com.example.soa.projectZavala.repository;

public interface AdministradorRepository {

    String obtenerNombreSiCredencialesSonValidas(String correo, String contraseña);
}

/*
    boolean insertarAdministrador(Administrador administrador);

    boolean updateAdministrador(Administrador administrador, int id);

    boolean deleteAdministrador(int id);

    List<Administrador> obtenerAdministradores();

    Administrador obtenerAdministradorPorId(int id);
    
    boolean validarCredenciales(String correo, String contraseña);
*/
