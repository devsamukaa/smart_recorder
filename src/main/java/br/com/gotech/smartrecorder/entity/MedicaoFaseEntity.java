package br.com.gotech.smartrecorder.entity;

import br.com.gotech.smartrecorder.helper.DateHelper;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_medicao_fase")
public class MedicaoFaseEntity implements Serializable {

    @Id
    @Column(name = "cd_medicao_fase")
    @SequenceGenerator(name = "medicao_fase",sequenceName = "sq_medicao_fase", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "medicao_fase")
    private Long cdMedicaoFase;

    @Column(name = "bl_medicao_com_dispositivo", nullable = false)
    private boolean isMedicaoDispositivo;

    @Column(name = "vl_kwh")
    private Double kwh;

    @Column(name = "vl_tensao")
    private Double tensao;

    @Column(name = "vl_corrente")
    private Double corrente;

    @Column(name = "vl_kwh_relogio")
    private Double kwhRelogio;

    @Column(name = "vl_kwh_ultima_conta")
    private Double kwhUltimaConta;

    @Column(name = "dt_medicao",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMedicao;

    @ManyToOne(optional = false)
    @JoinColumn(name="cd_fase")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private FaseEntity fase;

    @ManyToOne(optional = false)
    @JoinColumn(name="cd_instalacao")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private InstalacaoEntity instalacao;

    public MedicaoFaseEntity() {
    }

    public MedicaoFaseEntity(Long cdMedicaoFase, boolean isMedicaoDispositivo, Double kwh, Double tensao, Double corrente, Double kwhRelogio, Double kwhUltimaConta, Date dataMedicao, FaseEntity fase, InstalacaoEntity instalacao) {
        this.cdMedicaoFase = cdMedicaoFase;
        this.isMedicaoDispositivo = isMedicaoDispositivo;
        this.kwh = kwh;
        this.tensao = tensao;
        this.corrente = corrente;
        this.kwhRelogio = kwhRelogio;
        this.kwhUltimaConta = kwhUltimaConta;
        this.dataMedicao = dataMedicao;
        this.fase = fase;
        this.instalacao = instalacao;
    }

    public Long getCdMedicaoFase() { return cdMedicaoFase; }

    public void setCdMedicaoFase(Long idMedicaoFase) { this.cdMedicaoFase = idMedicaoFase; }

    public boolean isIsMedicaoDispositivo() { return isMedicaoDispositivo; }

    public void setIsMedicaoDispositivo(boolean medicaoDispositivo) { this.isMedicaoDispositivo = medicaoDispositivo; }

    public Double getKwh() { return kwh; }

    public void setKwh(Double kwhRelogio) { this.kwh = kwhRelogio; }

    public Double getTensao() { return tensao; }

    public void setTensao(Double tensao) { this.tensao = tensao; }

    public Double getCorrente() { return corrente; }

    public void setCorrente(Double corrente) { this.corrente = corrente; }

    public Date getDataMedicao() { return dataMedicao; }

    public void setDataMedicao(Date dtMedicao) { this.dataMedicao = dtMedicao; }

    public void setDataMedicao(String dtMedicao) { this.dataMedicao = DateHelper.parseDateCustom(dtMedicao, "dd/MM/yyyy HH:mm:ss"); }

    public FaseEntity getFase() {
        return fase;
    }

    public void setFase(FaseEntity fase) {
        this.fase = fase;
    }

    public InstalacaoEntity getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(InstalacaoEntity instalacao) {
        this.instalacao = instalacao;
    }

    public Double getKwhRelogio() {
        return kwhRelogio;
    }

    public void setKwhRelogio(Double kwh_relogio) {
        this.kwhRelogio = kwh_relogio;
    }

    public Double getKwhUltimaConta() {
        return kwhUltimaConta;
    }

    public void setKwhUltimaConta(Double kwh_ultima_conta) {
        this.kwhUltimaConta = kwh_ultima_conta;
    }


}
