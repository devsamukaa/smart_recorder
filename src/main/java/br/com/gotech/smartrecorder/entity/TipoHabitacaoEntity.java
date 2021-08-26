package br.com.gotech.smartrecorder.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_tipo_habitacao")
public class TipoHabitacaoEntity implements Serializable {

    @Id
    @Column(name = "cd_tipo_habitacao", nullable = false)
    @SequenceGenerator(name = "tipo_habitacao", sequenceName = "sq_tipo_habitacao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tipo_habitacao")
    private Long cdTipoHabitacao;

    @Column(name = "nm_tipo_habitacao", nullable = false, length = 100)
    private String nome;

    @Column(name = "vl_tusd", nullable = false)
    private Double valorTusd;

    @Column(name = "vl_te", nullable = false)
    private Double valorTe;

    public TipoHabitacaoEntity(){
    }

    public TipoHabitacaoEntity(Long cdTipoHabitacao, String nome, Double valorTusd, Double valorTe) {
        this.cdTipoHabitacao = cdTipoHabitacao;
        this.nome = nome;
        this.valorTusd = valorTusd;
        this.valorTe = valorTe;
    }

    public Long getCdTipoHabitacao() { return cdTipoHabitacao;
    }

    public void setCdTipoHabitacao(Long cdTipoHabitacao) { this.cdTipoHabitacao = cdTipoHabitacao;
    }

    public String getNome() { return nome;
    }

    public void setNome(String nmTipoHabitacao) { this.nome = nmTipoHabitacao;
    }

    public Double getValorTusd() {
        return valorTusd;
    }

    public void setValorTusd(Double valorTusd) {
        this.valorTusd = valorTusd;
    }

    public Double getValorTe() {
        return valorTe;
    }

    public void setValorTe(Double valorTe) {
        this.valorTe = valorTe;
    }
}
