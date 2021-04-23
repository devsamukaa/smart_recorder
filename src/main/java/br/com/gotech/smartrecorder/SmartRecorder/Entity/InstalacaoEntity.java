package br.com.gotech.smartrecorder.SmartRecorder.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_instalacao")
public class InstalacaoEntity implements Serializable {

    @Id
    @Column(name = "cd_instalacao")
    @SequenceGenerator(name = "instalacao",sequenceName = "sq_instalacao",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "instalacao")
    private Long idInstalacao;

    @Column(name = "bl_instalacao_ativa",nullable = false)
    private boolean ativa;

    @OneToOne(optional = false)
    private EnderecoEntity idEndereco;

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private TipoHabitacaoEntity cdTipoHabitacao;

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private PessoaEntity cdPessoa;

    public InstalacaoEntity() {
    }

    public InstalacaoEntity(Long idInstalacao, boolean ativa, EnderecoEntity idEndereco, TipoHabitacaoEntity cdTipoHabitacao, PessoaEntity cdPessoa) {
        this.idInstalacao = idInstalacao;
        this.ativa = ativa;
        this.idEndereco = idEndereco;
        this.cdTipoHabitacao = cdTipoHabitacao;
        this.cdPessoa = cdPessoa;
    }

    public Long getIdInstalacao() { return idInstalacao; }

    public void setIdInstalacao(Long idInstalacao) { this.idInstalacao = idInstalacao; }

    public boolean isAtiva() { return ativa; }

    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public EnderecoEntity getIdEndereco() { return idEndereco; }

    public void setIdEndereco(EnderecoEntity idEndereco) { this.idEndereco = idEndereco; }

    public TipoHabitacaoEntity getCdTipoHabitacao() { return cdTipoHabitacao; }

    public void setCdTipoHabitacao(TipoHabitacaoEntity cdTipoHabitacao) { this.cdTipoHabitacao = cdTipoHabitacao; }

    public PessoaEntity getCdPessoa() { return cdPessoa; }

    public void setCdPessoa(PessoaEntity cdPessoa) { this.cdPessoa = cdPessoa; }
}
