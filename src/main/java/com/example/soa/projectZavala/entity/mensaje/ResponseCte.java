package com.example.soa.projectZavala.entity.mensaje;

public enum ResponseCte {
    OPERACION_CORRECTA("001", "Operación correcta", "INFO"),
    ERROR_GENERAL("002", "Error general", "ERROR");

    private String codigo;
    private String mensaje;
    private String tipo;

    private ResponseCte(String codigo, String mensaje, String tipo) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    // Getters y setters (opcional) para las variables de instancia

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTipo() {
        return tipo;
    }
}
