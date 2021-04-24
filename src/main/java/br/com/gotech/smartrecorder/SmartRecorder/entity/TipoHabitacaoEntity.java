package br.com.gotech.smartrecorder.SmartRecorder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipoHabitacaoEntity implements Serializable {

    @Id
    @Column(name = "cd_tipo_habitacao", nullable = false)
    @SequenceGenerator(name = "tipo_habitacao", sequenceName = "sq_tipo_habitacao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tipo_habitacao")
    private Long cdTipoHabitacao;

    @Column(name = "nm_tipo", nullable = false, length = 100)
    private String nmTipoHabitacao;

    public TipoHabitacaoEntity(){
    }

    public TipoHabitacaoEntity(Long cdTipoHabitacao, String nmTipoHabitacao) {
        this.cdTipoHabitacao = cdTipoHabitacao;
        this.nmTipoHabitacao = nmTipoHabitacao;
    }

    public Long getCdTipoHabitacao() { return cdTipoHabitacao;
    }

    public void setCdTipoHabitacao(Long cdTipoHabitacao) { this.cdTipoHabitacao = cdTipoHabitacao;
    }

    public String getNmTipoHabitacao() { return nmTipoHabitacao;
    }

    public void setNmTipoHabitacao(String nmTipoHabitacao) { this.nmTipoHabitacao = nmTipoHabitacao;
    }
}
