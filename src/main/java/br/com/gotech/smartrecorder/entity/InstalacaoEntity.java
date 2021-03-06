package br.com.gotech.smartrecorder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_instalacao")
public class InstalacaoEntity implements Serializable {

    @Id
    @Column(name = "cd_instalacao", nullable = false)
    @SequenceGenerator(name = "instalacao",sequenceName = "sq_instalacao",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "instalacao")
    private Long cdInstalacao;

    @Column(name = "bl_instalacao_ativa",nullable = false)
    private boolean isAtiva;

    @OneToOne(optional = false)
    @JoinColumn(name="cd_endereco", unique = true)
    private EnderecoEntity endereco;

    @ManyToOne(optional = false)
    @JoinColumn(name="cd_tipo_habitacao")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private TipoHabitacaoEntity tipoHabitacao;

    @ManyToOne(optional = false)
    @JoinColumn(name="cd_pessoa")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private PessoaEntity pessoa;

    @Transient
    List<FaseEntity> fases;

    public InstalacaoEntity() {
    }

    public InstalacaoEntity(Long cdInstalacao, boolean isAtiva, EnderecoEntity endereco, TipoHabitacaoEntity tipoHabitacao, PessoaEntity pessoa) {
        this.cdInstalacao = cdInstalacao;
        this.isAtiva = isAtiva;
        this.endereco = endereco;
        this.tipoHabitacao = tipoHabitacao;
        this.pessoa = pessoa;
    }

    public Long getCdInstalacao() { return cdInstalacao; }

    public void setCdInstalacao(Long idInstalacao) { this.cdInstalacao = idInstalacao; }

    public boolean isIsAtiva() { return isAtiva; }

    public void setIsAtiva(boolean ativa) { this.isAtiva = ativa; }

    public EnderecoEntity getEndereco() { return endereco; }

    public void setEndereco(EnderecoEntity idEndereco) { this.endereco = idEndereco; }

    public TipoHabitacaoEntity getTipoHabitacao() { return tipoHabitacao; }

    public void setTipoHabitacao(TipoHabitacaoEntity cdTipoHabitacao) { this.tipoHabitacao = cdTipoHabitacao; }

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public PessoaEntity getPessoa() { return pessoa; }

    public void setPessoa(PessoaEntity cdPessoa) { this.pessoa = cdPessoa; }

    public List<FaseEntity> getFases() {
        return fases;
    }

    public void setFases(List<FaseEntity> fases) {
        this.fases = fases;
    }
}
