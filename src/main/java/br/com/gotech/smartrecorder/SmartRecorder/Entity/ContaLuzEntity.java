package br.com.gotech.smartrecorder.SmartRecorder.Entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class ContaLuzEntity implements Serializable {

    @Id
    @Column(name = "cd_conta_luz")
    @SequenceGenerator(name = "conta_luz", sequenceName = "sq_conta_luz", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_luz")
    private long idContaLuz;

    @Column(name = "qt_kwh_conta", nullable = false)
    private long kwhConta;

    @Column(name = "vl_pagto_conta", nullable = false)
    private long pagamento;

    @Column(name = "dt_validade_conta", nullable = false)
    private Date validade;

    @Column(name = " dt_registro_conta")
    private Date registro;

    @ManyToOne(optional = false)
    @Cascade(CascadeType.SAVE_UPDATE)
    private long cdInstalacao;

    public ContaLuzEntity() {
    }

    public ContaLuzEntity(long idContaLuz, long kwhConta, long pagamento, Date validade, Date registro, long cdInstalacao) {
        this.idContaLuz = idContaLuz;
        this.kwhConta = kwhConta;
        this.pagamento = pagamento;
        this.validade = validade;
        this.registro = registro;
        this.cdInstalacao = cdInstalacao;
    }

    public long getIdContaLuz() { return idContaLuz; }

    public void setIdContaLuz(long idContaLuz) { this.idContaLuz = idContaLuz; }

    public long getKwhConta() { return kwhConta; }

    public void setKwhConta(long kwhConta) { this.kwhConta = kwhConta; }

    public long getPagamento() { return pagamento; }

    public void setPagamento(long pagamento) { this.pagamento = pagamento; }

    public Date getValidade() { return validade; }

    public void setValidade(Date validade) { this.validade = validade; }

    public Date getRegistro() { return registro; }

    public void setRegistro(Date registro) { this.registro = registro; }

    public long getCdInstalacao() { return cdInstalacao; }

    public void setCdInstalacao(long cdInstalacao) { this.cdInstalacao = cdInstalacao; }
}
