package com.example.soa.projectZavala.entity.mensaje;

public class MensajeMeta {
    private String codigo;
    private String mensaje;
    private String tipo;

    public MensajeMeta(String codigo, String mensaje, String tipo) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
