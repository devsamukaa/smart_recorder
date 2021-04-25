package br.com.gotech.smartrecorder.entity;

import br.com.gotech.smartrecorder.helper.DateHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_conta_luz")
public class ContaLuzEntity implements Serializable {

    @Id
    @Column(name = "cd_conta_luz")
    @SequenceGenerator(name = "conta_luz", sequenceName = "sq_conta_luz", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_luz")
    private Long cdContaLuz;

    @Column(name = "qt_kwh_conta", nullable = false)
    private Double kwhConta;

    @Column(name = "vl_pagto_conta", nullable = false)
    private Double valorPago;

    @Column(name = "vl_pagto_tributos", nullable = false)
    private Double valorTributos;

    @Column(name = "dt_validade_conta", nullable = false)
    private Date dataValidade;

    @Column(name = " dt_registro_conta")
    private Date dataRegistro;

    @ManyToOne
    @JoinColumn(name="cd_instalacao")
    private InstalacaoEntity instalacao;

    public ContaLuzEntity() {
    }

    public ContaLuzEntity(Long cdContaLuz, Double kwhConta, Double valorPago, Double valorTributos, Date dataValidade, Date dataRegistro, InstalacaoEntity instalacao) {
        this.cdContaLuz = cdContaLuz;
        this.kwhConta = kwhConta;
        this.valorPago = valorPago;
        this.valorTributos = valorTributos;
        this.dataValidade = dataValidade;
        this.dataRegistro = dataRegistro;
        this.instalacao = instalacao;
    }

    public Long getCdContaLuz() { return cdContaLuz; }

    public void setCdContaLuz(Long idContaLuz) { this.cdContaLuz = idContaLuz; }

    public Double getKwhConta() { return kwhConta; }

    public void setKwhConta(Double kwhConta) { this.kwhConta = kwhConta; }

    public Double getValorPago() { return valorPago; }

    public void setValorPago(Double pagamento) { this.valorPago = pagamento; }

    public Double getValorTributos() {
        return valorTributos;
    }

    public void setValorTributos(Double valorTributos) {
        this.valorTributos = valorTributos;
    }

    public Date getDataValidade() { return dataValidade; }

    public void setDataValidade(Date dataValidade) { this.dataValidade = dataValidade; }

    public void setDataValidade(String dataValidade) { this.dataValidade = DateHelper.parseDate(dataValidade); }

    public Date getDataRegistro() { return dataRegistro; }

    public void setDataRegistro(Date dataRegistro) { this.dataRegistro = dataRegistro; }

    public void setDataRegistro(String dataRegistro) { this.dataRegistro = DateHelper.parseDate(dataRegistro); }

    public InstalacaoEntity getInstalacao() { return instalacao; }

    public void setInstalacao(InstalacaoEntity cdInstalacao) { this.instalacao = cdInstalacao; }
}
