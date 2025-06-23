package com.example.soa.projectZavala.repository;

import java.util.List;
import java.sql.Date;

import com.example.soa.projectZavala.entity.Consulta;

public interface ConsultaRepository {
	  List<Consulta> obtenerVehiculosMantenimientoPrevio(Date fechaLimite, int servicioId);
}
