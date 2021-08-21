package br.com.gotech.smartrecorder.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_plano")
public class PlanoEntity implements Serializable {

  @Id
  @Column(name = "cd_plano", nullable = false)
  @SequenceGenerator(name = "plano", sequenceName = "sq_cd_plano", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "plano")
  private Long cdPlano;

  @Column(name = "nm_plano", nullable = false, length = 100)
  private String nome;

  @Column(name = "vl_adesao")
  private Double valorAdesao;

  @Column(name = "vl_mensalidade")
  private Double valorMensalidade;

  public PlanoEntity() {
  }

  public PlanoEntity(Long cdPlano, String nome, Double valorAdesao, Double valorMensalidade) {
    this.cdPlano = cdPlano;
    this.nome = nome;
    this.valorAdesao = valorAdesao;
    this.valorMensalidade = valorMensalidade;
  }

  public Long getCdPlano() {
    return cdPlano;
  }

  public void setCdPlano(Long cdPlano) {
    this.cdPlano = cdPlano;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getValorAdesao() {
    return valorAdesao;
  }

  public void setValorAdesao(Double valorAdesao) {
    this.valorAdesao = valorAdesao;
  }

  public Double getValorMensalidade() {
    return valorMensalidade;
  }

  public void setValorMensalidade(Double valorMensalidade) {
    this.valorMensalidade = valorMensalidade;
  }
}
