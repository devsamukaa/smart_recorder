package br.com.gotech.smartrecorder.entity.business;

import br.com.gotech.smartrecorder.entity.BandeiraEntity;

import java.io.Serializable;

public class BusinessConsumo implements Serializable {

    private Double kwh;
    private Double custoUsoSistDistribuicaoIcms;
    private Double custoUsoSistDistribuicaoTotal;
    private Double custoEnergiaTeTotal;
    private Double custoEnergiaTeIcms;
    private Double custoAdicionalBandeiraTotal;
    private Double custoAdicionalBandeiraIcms;
    private Double custoPisPasepTotal;
    private Double custoPisPasepIcms;
    private Double custoCofinsTotal;
    private Double custoCofinsIcms;
    private Double custoCipCosip;
    private Double totalTributos;
    private Double icms;
    private Double custoTotal;
    private BandeiraEntity bandeira;

    public BusinessConsumo() {
    }

    public Double getKwh() {
        return kwh;
    }

    public void setKwh(Double kwh) {
        this.kwh = kwh;
    }

    public Double getCustoUsoSistDistribuicaoIcms() {
        return custoUsoSistDistribuicaoIcms;
    }

    public void setCustoUsoSistDistribuicaoIcms(Double custoUsoSistDistribuicaoIcms) {
        this.custoUsoSistDistribuicaoIcms = custoUsoSistDistribuicaoIcms;
    }

    public Double getCustoUsoSistDistribuicaoTotal() {
        return custoUsoSistDistribuicaoTotal;
    }

    public void setCustoUsoSistDistribuicaoTotal(Double custoUsoSistDistribuicaoTotal) {
        this.custoUsoSistDistribuicaoTotal = custoUsoSistDistribuicaoTotal;
    }

    public Double getCustoEnergiaTeTotal() {
        return custoEnergiaTeTotal;
    }

    public void setCustoEnergiaTeTotal(Double custoEnergiaTeTotal) {
        this.custoEnergiaTeTotal = custoEnergiaTeTotal;
    }

    public Double getCustoEnergiaTeIcms() {
        return custoEnergiaTeIcms;
    }

    public void setCustoEnergiaTeIcms(Double custoEnergiaTeIcms) {
        this.custoEnergiaTeIcms = custoEnergiaTeIcms;
    }

    public Double getCustoAdicionalBandeiraTotal() {
        return custoAdicionalBandeiraTotal;
    }

    public void setCustoAdicionalBandeiraTotal(Double custoAdicionalBandeiraTotal) {
        this.custoAdicionalBandeiraTotal = custoAdicionalBandeiraTotal;
    }

    public Double getCustoAdicionalBandeiraIcms() {
        return custoAdicionalBandeiraIcms;
    }

    public void setCustoAdicionalBandeiraIcms(Double custoAdicionalBandeiraIcms) {
        this.custoAdicionalBandeiraIcms = custoAdicionalBandeiraIcms;
    }

    public Double getCustoPisPasepTotal() {
        return custoPisPasepTotal;
    }

    public void setCustoPisPasepTotal(Double custoPisPasepTotal) {
        this.custoPisPasepTotal = custoPisPasepTotal;
    }

    public Double getCustoPisPasepIcms() {
        return custoPisPasepIcms;
    }

    public void setCustoPisPasepIcms(Double custoPisPasepIcms) {
        this.custoPisPasepIcms = custoPisPasepIcms;
    }

    public Double getCustoCofinsTotal() {
        return custoCofinsTotal;
    }

    public void setCustoCofinsTotal(Double custoCofinsTotal) {
        this.custoCofinsTotal = custoCofinsTotal;
    }

    public Double getCustoCofinsIcms() {
        return custoCofinsIcms;
    }

    public void setCustoCofinsIcms(Double custoCofinsIcms) {
        this.custoCofinsIcms = custoCofinsIcms;
    }

    public Double getCustoCipCosip() {
        return custoCipCosip;
    }

    public void setCustoCipCosip(Double custoCipCosip) {
        this.custoCipCosip = custoCipCosip;
    }

    public Double getTotalTributos() {
        return totalTributos;
    }

    public void setTotalTributos(Double totalTributos) {
        this.totalTributos = totalTributos;
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(Double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public BandeiraEntity getBandeira() {
        return bandeira;
    }

    public void setBandeira(BandeiraEntity bandeira) {
        this.bandeira = bandeira;
    }
}


