package com.example.soa.projectZavala.entity.mensaje;

public class MetaModel {
    private MensajeMeta mensaje;
    private int totalRegistros;
    private String idTransaccion;
    private int pagina;
    private boolean resultado;

    public MetaModel() {
    }

    public MetaModel(MensajeMeta mensaje, int totalRegistros, String idTransaccion, int pagina, boolean resultado) {
        this.mensaje = mensaje;
        this.totalRegistros = totalRegistros;
        this.idTransaccion = idTransaccion;
        this.pagina = pagina;
        this.resultado = resultado;
    }

    public MensajeMeta getMensaje() {
        return mensaje;
    }

    public void setMensaje(MensajeMeta mensaje) {
        this.mensaje = mensaje;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
}