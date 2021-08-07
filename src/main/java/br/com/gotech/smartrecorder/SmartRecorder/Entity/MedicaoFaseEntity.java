package br.com.gotech.smartrecorder.SmartRecorder.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_medicao_fase")
public class MedicaoFaseEntity implements Serializable {

    @Id
    @Column(name = "cd_medicao_fase")
    /*@SequenceGenerator(name = "medicao_fase",sequenceName = "sq_medicao_fase", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "medicao_fase")*/
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idMedicaoFase;

    @Column(name = "bl_medicao_com_dispositivo", nullable = false)
    private boolean medicaoDispositivo;

    @Column(name = "vl_kwh_relogio", nullable = false)
    private Long kwhRelogio;

    @Column(name = "vl_tensao", nullable = false)
    private Long tensao;

    @Column(name = "vl_corrente", nullable = false)
    private Long corrente;

    @Column(name = "dt_medicao",nullable = false)
    private Date dtMedicao;

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private FaseEntity idFase;

    public MedicaoFaseEntity() {
    }

    public MedicaoFaseEntity(Long idMedicaoFase, boolean medicaoDispositivo, Long kwhRelogio, Long tensao, Long corrente, Date dtMedicao) {
        this.idMedicaoFase = idMedicaoFase;
        this.medicaoDispositivo = medicaoDispositivo;
        this.kwhRelogio = kwhRelogio;
        this.tensao = tensao;
        this.corrente = corrente;
        this.dtMedicao = dtMedicao;
    }

    public Long getIdMedicaoFase() { return idMedicaoFase; }

    public void setIdMedicaoFase(Long idMedicaoFase) { this.idMedicaoFase = idMedicaoFase; }

    public boolean isMedicaoDispositivo() { return medicaoDispositivo; }

    public void setMedicaoDispositivo(boolean medicaoDispositivo) { this.medicaoDispositivo = medicaoDispositivo; }

    public Long getKwhRelogio() { return kwhRelogio; }

    public void setKwhRelogio(Long kwhRelogio) { this.kwhRelogio = kwhRelogio; }

    public Long getTensao() { return tensao; }

    public void setTensao(Long tensao) { this.tensao = tensao; }

    public Long getCorrente() { return corrente; }

    public void setCorrente(Long corrente) { this.corrente = corrente; }

    public Date getDtMedicao() { return dtMedicao; }

    public void setDtMedicao(Date dtMedicao) { this.dtMedicao = dtMedicao; }
}
