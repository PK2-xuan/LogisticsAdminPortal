package com.example.soa.projectZavala.dto;

public class LoginResponse {
	 private Integer idAdministrador;
	    private String nombre;

	    public LoginResponse(Integer idAdministrador, String nombre) {
	        this.idAdministrador = idAdministrador;
	        this.nombre = nombre;
	    }

	    public Integer getIdAdministrador() {
	        return idAdministrador;
	    }

	    public void setIdAdministrador(Integer idAdministrador) {
	        this.idAdministrador = idAdministrador;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
}
