package com.example.soa.projectZavala.entity.mensaje;

public class HttpResponseModel {

    private MetaModel meta;
    private Object data;

    public HttpResponseModel(MetaModel meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    public MetaModel getMeta() {
        return meta;
    }

    public void setMeta(MetaModel meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
