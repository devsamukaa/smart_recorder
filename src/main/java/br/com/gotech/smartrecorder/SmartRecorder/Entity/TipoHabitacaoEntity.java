package br.com.gotech.smartrecorder.SmartRecorder.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipoHabitacaoEntity implements Serializable {

    @Id
    @Column(name = "cd_tipo_habitacao", nullable = false)
    @SequenceGenerator(name = "tipo_habitacao", sequenceName = "sq_tipo_habitacao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tipo_habitacao")
    private Long cdTipoAtividade;

    @Column(name = "nm_tipo", nullable = false, length = 100)
    private String nmTipoHabitacao;

    public TipoHabitacaoEntity(){
    }

    public TipoHabitacaoEntity(Long cdTipoAtividade, String nmTipoHabitacao) {
        this.cdTipoAtividade = cdTipoAtividade;
        this.nmTipoHabitacao = nmTipoHabitacao;
    }

    public Long getCdTipoAtividade() { return cdTipoAtividade;
    }

    public void setCdTipoAtividade(Long cdTipoAtividade) { this.cdTipoAtividade = cdTipoAtividade;
    }

    public String getNmTipoHabitacao() { return nmTipoHabitacao;
    }

    public void setNmTipoHabitacao(String nmTipoHabitacao) { this.nmTipoHabitacao = nmTipoHabitacao;
    }
}
