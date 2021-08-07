package br.com.gotech.smartrecorder.entity;

import br.com.gotech.smartrecorder.entity.enum_classes.IdentificadorFase;
import br.com.gotech.smartrecorder.entity.enum_classes.TipoPessoa;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_fase")
public class FaseEntity implements Serializable {

    @Id
    @Column(name = "cd_fase",nullable = false)
    /*@SequenceGenerator(name = "fase",sequenceName = "sq_fase",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "fase")*/
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long cdFase;

    @Column(name = "nm_fase",nullable = false)
    private String nome;

    @Column(name = "idt_fase",nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentificadorFase identificadorFase;

    @ManyToOne(optional = false)
    @JoinColumn(name="cd_instalacao")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private InstalacaoEntity instalacao;

    public FaseEntity() {
    }

    public FaseEntity(Long cdFase, String nome, IdentificadorFase identificadorFase, InstalacaoEntity instalacao) {
        this.cdFase = cdFase;
        this.nome = nome;
        this.identificadorFase = identificadorFase;
        this.instalacao = instalacao;
    }

    public Long getCdFase() { return cdFase; }

    public void setCdFase(Long idFase) { this.cdFase = idFase; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public IdentificadorFase getIdentificadorFase() { return identificadorFase; }

    public void setIdentificadorFase(IdentificadorFase idt) { this.identificadorFase = idt; }

    public void setIdentificadorFase(String idt) {
        this.identificadorFase = idt.equals("NEUTRO") ? IdentificadorFase.NEUTRO : IdentificadorFase.FASE;
    }

    public InstalacaoEntity getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(InstalacaoEntity instalacao) {
        this.instalacao = instalacao;
    }


}
