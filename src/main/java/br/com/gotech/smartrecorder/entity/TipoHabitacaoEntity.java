package br.com.gotech.smartrecorder.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipoHabitacaoEntity implements Serializable {

    @Id
    @Column(name = "cd_tipo_habitacao", nullable = false)
    /*@SequenceGenerator(name = "tipo_habitacao", sequenceName = "sq_tipo_habitacao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tipo_habitacao")*/
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long cdTipoHabitacao;

    @Column(name = "nm_tipo", nullable = false, length = 100)
    private String nome;

    public TipoHabitacaoEntity(){
    }

    public TipoHabitacaoEntity(Long cdTipoHabitacao, String nmTipoHabitacao) {
        this.cdTipoHabitacao = cdTipoHabitacao;
        this.nome = nmTipoHabitacao;
    }

    public Long getCdTipoHabitacao() { return cdTipoHabitacao;
    }

    public void setCdTipoHabitacao(Long cdTipoHabitacao) { this.cdTipoHabitacao = cdTipoHabitacao;
    }

    public String getNome() { return nome;
    }

    public void setNome(String nmTipoHabitacao) { this.nome = nmTipoHabitacao;
    }
}
