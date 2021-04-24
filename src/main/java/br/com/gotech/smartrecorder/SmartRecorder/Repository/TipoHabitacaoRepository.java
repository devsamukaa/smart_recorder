package br.com.gotech.smartrecorder.SmartRecorder.Repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.TipoHabitacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoHabitacaoRepository extends JpaRepository<TipoHabitacaoEntity, Long> {

    List<TipoHabitacaoEntity> findbyNmTipoHabitacao(String nmTipoHabitacao);
    List<TipoHabitacaoEntity> findByCdTipoHabitacao(Long cdTipoHabitacao);
}
