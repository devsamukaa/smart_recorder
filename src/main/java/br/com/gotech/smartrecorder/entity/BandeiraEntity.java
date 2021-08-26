package br.com.gotech.smartrecorder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_bandeira")
public class BandeiraEntity implements Serializable {

    @Id
    @Column(name = "cd_bandeira", nullable = false)
    @SequenceGenerator(name = "bandeira", sequenceName = "sq_bandeira", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bandeira")
    private Long cdBandeira;

    @Column(name = "nm_bandeira", nullable = false, length = 100)
    private String nome;

    @Column(name = "vl_adc_bandeira", nullable = false)
    private Double valorAdicional;

    @Column(name = "bl_bandeira_ativa", nullable = false)
    private Boolean isBandeiraAtiva;

    public BandeiraEntity() {
    }

    public BandeiraEntity(Long cdBandeira, String nome, Double valorAdicional, Boolean isBandeiraAtiva) {
        this.cdBandeira = cdBandeira;
        this.nome = nome;
        this.valorAdicional = valorAdicional;
        this.isBandeiraAtiva = isBandeiraAtiva;
    }

    public Long getCdBandeira() {
        return cdBandeira;
    }

    public void setCdBandeira(Long cdBandeira) {
        this.cdBandeira = cdBandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeBandeira) {
        this.nome = nomeBandeira;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicionalBandeira) {
        this.valorAdicional = valorAdicionalBandeira;
    }

    public Boolean getBandeiraAtiva() {
        return isBandeiraAtiva;
    }

    public void setBandeiraAtiva(Boolean bandeiraAtiva) {
        isBandeiraAtiva = bandeiraAtiva;
    }
}
