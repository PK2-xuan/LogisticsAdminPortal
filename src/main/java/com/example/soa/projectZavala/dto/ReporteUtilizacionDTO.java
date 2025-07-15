package com.example.soa.projectZavala.dto;

public class ReporteUtilizacionDTO {
	private Integer idVehiculo;
	private String placa;
	private Integer idServicio;
	private String nombreServicio;
	private Integer vecesAsignado;
	private String fechasAsignacion;

	public ReporteUtilizacionDTO() {
		super();
	}

	public ReporteUtilizacionDTO(Integer idVehiculo, String placa, Integer idServicio, String nombreServicio,
			Integer vecesAsignado, String fechasAsignacion) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
		this.vecesAsignado = vecesAsignado;
		this.fechasAsignacion = fechasAsignacion;
	}

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Integer getVecesAsignado() {
		return vecesAsignado;
	}

	public void setVecesAsignado(Integer vecesAsignado) {
		this.vecesAsignado = vecesAsignado;
	}

	public String getFechasAsignacion() {
		return fechasAsignacion;
	}

	public void setFechasAsignacion(String fechasAsignacion) {
		this.fechasAsignacion = fechasAsignacion;
	}

}
