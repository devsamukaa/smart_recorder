package br.com.gotech.smartrecorder.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
    private String nomeBandeira;

    @Column(name = "vl_adc_bandeira", nullable = false)
    private Double valorAdicionalBandeira;

    @Column(name = "bl_bandeira_ativa", nullable = false)
    private Boolean isBandeiraAtiva;

    public BandeiraEntity() {
    }

    public BandeiraEntity(Long cdBandeira, String nomeBandeira, Double valorAdicionalBandeira, Boolean isBandeiraAtiva) {
        this.cdBandeira = cdBandeira;
        this.nomeBandeira = nomeBandeira;
        this.valorAdicionalBandeira = valorAdicionalBandeira;
        this.isBandeiraAtiva = isBandeiraAtiva;
    }

    public Long getCdBandeira() {
        return cdBandeira;
    }

    public void setCdBandeira(Long cdBandeira) {
        this.cdBandeira = cdBandeira;
    }

    public String getNomeBandeira() {
        return nomeBandeira;
    }

    public void setNomeBandeira(String nomeBandeira) {
        this.nomeBandeira = nomeBandeira;
    }

    public Double getValorAdicionalBandeira() {
        return valorAdicionalBandeira;
    }

    public void setValorAdicionalBandeira(Double valorAdicionalBandeira) {
        this.valorAdicionalBandeira = valorAdicionalBandeira;
    }

    public Boolean getBandeiraAtiva() {
        return isBandeiraAtiva;
    }

    public void setBandeiraAtiva(Boolean bandeiraAtiva) {
        isBandeiraAtiva = bandeiraAtiva;
    }
}
