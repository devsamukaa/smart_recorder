package br.com.gotech.smartrecorder.SmartRecorder.repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.MetaConsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MetaConsumoRepository extends JpaRepository<MetaConsumoEntity, Long> {

    List<MetaConsumoEntity> findByDtCriacaoMeta(Date dtCriacaoMeta);
    List<MetaConsumoEntity> findByVlMetaConsumo(Long vlMetaConsumo);
    List<MetaConsumoEntity> findByCdMetaConsumo(Long cdMetaConsumo);
}
