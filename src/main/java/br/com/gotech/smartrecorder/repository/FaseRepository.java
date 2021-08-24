package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.FaseEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaseRepository extends JpaRepository<FaseEntity, Long> {

  List<FaseEntity> findByInstalacaoCdInstalacao (Long cdInstalacao);

}
