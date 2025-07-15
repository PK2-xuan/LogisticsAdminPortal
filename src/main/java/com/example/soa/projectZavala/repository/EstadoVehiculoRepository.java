package com.example.soa.projectZavala.repository;

import java.util.List;

import com.example.soa.projectZavala.dto.ReporteUtilizacionDTO;
import com.example.soa.projectZavala.entity.EstadoVehiculo;

public interface EstadoVehiculoRepository {

    boolean insertarEstadoVehiculo(EstadoVehiculo estado);

    boolean updateEstadoVehiculo(EstadoVehiculo estado, int id);

    boolean deleteEstadoVehiculo(int id);

    List<EstadoVehiculo> obtenerEstadosVehiculo();

    EstadoVehiculo obtenerEstadoVehiculoPorId(int id);
    
    // SERVICIOS
    
    List<ReporteUtilizacionDTO> obtenerReporteUtilizacionVehiculos();
    
    void actualizarEstadoVehiculosPorMantenimiento();
}
