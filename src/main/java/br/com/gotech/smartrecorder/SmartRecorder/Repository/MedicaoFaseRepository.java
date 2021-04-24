package br.com.gotech.smartrecorder.SmartRecorder.Repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.MedicaoFaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MedicaoFaseRepository extends JpaRepository<MedicaoFaseEntity, Long> {

    List<MedicaoFaseEntity> findByDtMedicao(Date dtMedicao);

}
