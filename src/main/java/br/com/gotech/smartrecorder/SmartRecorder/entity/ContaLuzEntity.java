package br.com.gotech.smartrecorder.SmartRecorder.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_conta_luz")
public class ContaLuzEntity implements Serializable {

    @Id
    @Column(name = "cd_conta_luz")
    @SequenceGenerator(name = "conta_luz", sequenceName = "sq_conta_luz", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_luz")
    private Long idContaLuz;

    @Column(name = "qt_kwh_conta", nullable = false)
    private Long kwhConta;

    @Column(name = "vl_pagto_conta", nullable = false)
    private Long pagamento;

    @Column(name = "dt_validade_conta", nullable = false)
    private Date validade;

    @Column(name = " dt_registro_conta")
    private Date registro;

    @Column(name = "cd_instalacao")
    private Long cdInstalacao;

    public ContaLuzEntity() {
    }

    public ContaLuzEntity(Long idContaLuz, Long kwhConta, Long pagamento, Date validade, Date registro, Long cdInstalacao) {
        this.idContaLuz = idContaLuz;
        this.kwhConta = kwhConta;
        this.pagamento = pagamento;
        this.validade = validade;
        this.registro = registro;
        this.cdInstalacao = cdInstalacao;
    }

    public Long getIdContaLuz() { return idContaLuz; }

    public void setIdContaLuz(Long idContaLuz) { this.idContaLuz = idContaLuz; }

    public Long getKwhConta() { return kwhConta; }

    public void setKwhConta(Long kwhConta) { this.kwhConta = kwhConta; }

    public Long getPagamento() { return pagamento; }

    public void setPagamento(Long pagamento) { this.pagamento = pagamento; }

    public Date getValidade() { return validade; }

    public void setValidade(Date validade) { this.validade = validade; }

    public Date getRegistro() { return registro; }

    public void setRegistro(Date registro) { this.registro = registro; }

    public Long getCdInstalacao() { return cdInstalacao; }

    public void setCdInstalacao(Long cdInstalacao) { this.cdInstalacao = cdInstalacao; }
}
