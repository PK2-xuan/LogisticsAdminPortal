package com.example.soa.projectZavala.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soa.projectZavala.entity.Administrador;
import com.example.soa.projectZavala.service.AdministradorService;

@RestController
@RequestMapping("/api/zavala")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/login")
    public ResponseEntity<String> logeoYObtieneNombre(@RequestBody Administrador administrador) {
        String mensaje = administradorService.logeoYObtieneNombre(administrador.getCorreo(), administrador.getContraseña());
        return ResponseEntity.ok(mensaje);
    }
}


/*
@PostMapping("/logeo")
public ResponseEntity<String> logeoSimple(@RequestBody Administrador administrador) {
    String resultado = administradorService.logeoSimple(administrador.getCorreo(), administrador.getContraseña());
    return ResponseEntity.ok(resultado);
}
*/