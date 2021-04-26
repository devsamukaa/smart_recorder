package br.com.gotech.smartrecorder.entity;

import java.io.Serializable;

public class ConsumoEntity implements Serializable {

    private Double kwh;
    private Double custo;

    public ConsumoEntity() {
    }

    public ConsumoEntity(Double kwh, Double custo) {
        this.kwh = kwh;
        this.custo = custo;
    }

    public Double getKwh() {
        return kwh;
    }

    public void setKwh(Double kwh) {
        this.kwh = kwh;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }
}


