package br.com.gotech.smartrecorder.entity;

import br.com.gotech.smartrecorder.helper.DateHelper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_meta_consumo")
public class MetaConsumoEntity implements Serializable {
    @Id
    @Column(name = "cd_meta_consumo", nullable = false)
    /*@SequenceGenerator(name = "cd_meta", sequenceName = "sq_cd_meta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cd_meta")*/
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long cdMetaConsumo;

    @Column(name = "vl_meta_consumo", nullable = false)
    private Float valor;

    @Column(name = "dt_criacao_meta", nullable = false)
    private Date dataCriacao;

    public MetaConsumoEntity() {
    }

    public MetaConsumoEntity(Long cdMetaConsumo, Float valor, Date dataCriacao) {
        this.cdMetaConsumo = cdMetaConsumo;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }

    public Long getCdMetaConsumo() {
        return cdMetaConsumo;
    }

    public Float getValor() {
        return valor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setCdMetaConsumo(Long cdMetaConsumo) {
        this.cdMetaConsumo = cdMetaConsumo;
    }

    public void setValor(Float vlMetaConsumo) {
        this.valor = vlMetaConsumo;
    }

    public void setDataCriacao(Date dtCriacaoMeta) {
        this.dataCriacao = dtCriacaoMeta;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = DateHelper.parseDate(dataCriacao);
    }

}
