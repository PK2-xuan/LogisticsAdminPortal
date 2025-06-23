package com.example.soa.projectZavala.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.soa.projectZavala.entity.Consulta;
import com.example.soa.projectZavala.repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> obtenerVehiculosMantenimientoPrevio(Date fecha, int servicioId) {
        return consultaRepository.obtenerVehiculosMantenimientoPrevio(fecha, servicioId);
    }
}