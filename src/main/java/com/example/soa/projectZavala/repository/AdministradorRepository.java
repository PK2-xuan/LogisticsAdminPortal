package com.example.soa.projectZavala.repository;

public interface AdministradorRepository {

    String obtenerNombreSiCredencialesSonValidas(String correo, String contraseña);
}
