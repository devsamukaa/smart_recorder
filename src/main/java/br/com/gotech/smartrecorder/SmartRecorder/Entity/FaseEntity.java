package br.com.gotech.smartrecorder.SmartRecorder.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_fase")
public class FaseEntity implements Serializable {

    @Id
    @Column(name = "cd_fase",nullable = false)
    /* @SequenceGenerator(name = "fase",sequenceName = "sq_fase",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "fase") */
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idFase;

    @Column(name = "nm_fase",nullable = false)
    private String nome;

    @Column(name = "idt_fase",nullable = false)
    private String idt;

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private InstalacaoEntity idInstalacao;

    public FaseEntity() {
    }

    public FaseEntity(Long idFase, String nome, String idt) {
        this.idFase = idFase;
        this.nome = nome;
        this.idt = idt;
    }

    public Long getIdFase() { return idFase; }

    public void setIdFase(Long idFase) { this.idFase = idFase; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getIdt() { return idt; }

    public void setIdt(String idt) { this.idt = idt; }
}
