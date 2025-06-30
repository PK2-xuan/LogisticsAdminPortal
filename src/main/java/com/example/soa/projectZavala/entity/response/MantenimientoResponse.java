package com.example.soa.projectZavala.entity.response;

import java.util.List;
import com.example.soa.projectZavala.entity.Mantenimiento;

public class MantenimientoResponse {

    private List<Mantenimiento> listaMantenimientos;

    public List<Mantenimiento> getListaMantenimientos() {
        return listaMantenimientos;
    }

    public void setListaMantenimientos(List<Mantenimiento> listaMantenimientos) {
        this.listaMantenimientos = listaMantenimientos;
    }

    public List<Mantenimiento> obtenerListaMantenimientos() {
        return listaMantenimientos;
    }
    
}
