package com.example.soa.projectZavala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.example.soa.projectZavala.entity.Administrador;
//import com.example.soa.projectZavala.entity.response.AdministradorResponse;
import com.example.soa.projectZavala.repository.AdministradorRepository;
//import java.util.List;

@Service
@Transactional
public class AdministradorService {

	@Autowired
    private AdministradorRepository administradorRepository;


  
    
    public String logeoYObtieneNombre(String correo, String contraseña) {
        String nombre = administradorRepository.obtenerNombreSiCredencialesSonValidas(correo, contraseña);
        return (nombre != null) ? "Bienvenido, " + nombre : "Credenciales incorrectas";
    }
}

/*
 * public boolean delete(int id) {
        return administradorRepository.deleteAdministrador(id);
    }

    public boolean insert(Administrador administrador) {
        return administradorRepository.insertarAdministrador(administrador);
    }

    public boolean update(Administrador administrador, int id) {
        return administradorRepository.updateAdministrador(administrador, id);
    }

    public AdministradorResponse getList() {
        AdministradorResponse response = new AdministradorResponse();
        List<Administrador> lista = administradorRepository.obtenerAdministradores();
        response.setListaAdministradores(lista);
        return response;
    }

    public Administrador getById(int id) {
        return administradorRepository.obtenerAdministradorPorId(id);
    }
    
    public String logeoSimple(String correo, String contraseña) {
        boolean esValido = administradorRepository.validarCredenciales(correo, contraseña);
        return esValido ? "Correcto" : "No es correcto";
    }
 */



