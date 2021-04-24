package br.com.gotech.smartrecorder.SmartRecorder.repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.FaseEntity;
import br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaseRepository extends JpaRepository<FaseEntity, Long> {

    List<FaseEntity> findByIdFase(Long idFase);
    List<FaseEntity> findByIdInstalacao(InstalacaoEntity idInstalacao);
}
