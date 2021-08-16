package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.MedicaoFaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface MedicaoFaseRepository extends JpaRepository<MedicaoFaseEntity, Long> {

    List<MedicaoFaseEntity> findByDataMedicaoBetweenAndInstalacao_CdInstalacaoAndIsMedicaoDispositivoOrderByDataMedicaoAsc(Date dataDepoisDe, Date dataAntesDe, Long cdInstalacao, boolean isMedicaoDispositivo);

    MedicaoFaseEntity getByInstalacao_CdInstalacaoAndIsMedicaoDispositivoOrderByDataMedicaoDesc(Long cdInstalacao, boolean isMedicaoDispositivo);
}
