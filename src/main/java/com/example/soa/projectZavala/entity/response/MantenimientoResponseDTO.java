package com.example.soa.projectZavala.entity.response;

public class MantenimientoResponseDTO {

	private Integer idMantenimiento;
	private Integer vehiculoId;
	private String fechaProgramada;
	private String realizado;
	private String descripcion;
	private String estado;
	private Integer administradorId;

	public Integer getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(Integer idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public Integer getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(Integer vehiculoId) {
		this.vehiculoId = vehiculoId;
	}

	public String getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public String getRealizado() {
		return realizado;
	}

	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}

}
