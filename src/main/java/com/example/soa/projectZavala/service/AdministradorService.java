package com.example.soa.projectZavala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soa.projectZavala.dto.LoginResponse;
//import com.example.soa.projectZavala.entity.Administrador;
//import com.example.soa.projectZavala.entity.response.AdministradorResponse;
import com.example.soa.projectZavala.repository.AdministradorRepository;
//import java.util.List;

@Service
@Transactional
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;

	public LoginResponse logeo(String correo, String contraseña) {
		return administradorRepository.obtenerAdminPorCredenciales(correo, contraseña);
	}
}
