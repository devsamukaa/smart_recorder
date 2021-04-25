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

    @Column(name = "vl_kwh", nullable = false)
    private Double kwh;

    @Column(name = "vl_tensao", nullable = false)
    private Double tensao;

    @Column(name = "vl_corrente", nullable = false)
    private Double corrente;

    @Column(name = "dt_medicao",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMedicao;

    @ManyToOne(optional = false)
    @JoinColumn(name="cd_fase")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private FaseEntity fase;

    public MedicaoFaseEntity() {
    }

    public MedicaoFaseEntity(Long cdMedicaoFase, boolean isMedicaoDispositivo, Double kwh, Double tensao, Double corrente, Date dataMedicao, FaseEntity fase) {
        this.cdMedicaoFase = cdMedicaoFase;
        this.isMedicaoDispositivo = isMedicaoDispositivo;
        this.kwh = kwh;
        this.tensao = tensao;
        this.corrente = corrente;
        this.dataMedicao = dataMedicao;
        this.fase = fase;
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

}
