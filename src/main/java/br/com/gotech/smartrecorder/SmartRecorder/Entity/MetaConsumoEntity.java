package br.com.gotech.smartrecorder.SmartRecorder.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

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
    private Long vlMetaConsumo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_criacao_meta", nullable = false)
    private Calendar dtCriacaoMeta;

    @OneToOne
    private PessoaEntity pessoa;

    public MetaConsumoEntity() {
    }

    public Long getCdMetaConsumo() {
        return cdMetaConsumo;
    }

    public Long getVlMetaConsumo() {
        return vlMetaConsumo;
    }

    public Calendar getDtCriacaoMeta() {
        return dtCriacaoMeta;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setCdMetaConsumo(Long cdMetaConsumo) {
        this.cdMetaConsumo = cdMetaConsumo;
    }

    public void setVlMetaConsumo(Long vlMetaConsumo) {
        this.vlMetaConsumo = vlMetaConsumo;
    }

    public void setDtCriacaoMeta(Calendar dtCriacaoMeta) {
        this.dtCriacaoMeta = dtCriacaoMeta;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }
}
