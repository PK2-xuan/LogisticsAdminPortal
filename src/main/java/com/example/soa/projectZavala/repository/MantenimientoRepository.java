package com.example.soa.projectZavala.repository;

import java.util.List;
import com.example.soa.projectZavala.entity.Mantenimiento;

public interface MantenimientoRepository {

    boolean insertarMantenimiento(Mantenimiento mantenimiento);

    boolean updateMantenimiento(Mantenimiento mantenimiento, int id);

    boolean deleteMantenimiento(int id);

    List<Mantenimiento> obtenerMantenimientos();

    Mantenimiento obtenerMantenimientoPorId(int id);	
}
