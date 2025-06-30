package com.example.soa.projectZavala.repository;

import com.example.soa.projectZavala.dto.LoginResponse;

public interface AdministradorRepository {

    LoginResponse obtenerAdminPorCredenciales(String correo, String contraseña);
}
