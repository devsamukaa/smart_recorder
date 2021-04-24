package br.com.gotech.smartrecorder.SmartRecorder.Repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.ContaLuzEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ContaLuzRepository extends JpaRepository<ContaLuzEntity, Long> {

    List<ContaLuzEntity> findByIdContaLuz(Long idContaLuz);
    List<ContaLuzEntity> findByValidade(Date validade);
    List<ContaLuzEntity> findByRegistro(Date registro);
    List<ContaLuzEntity> findByCdInstalacao(Long cdInstalacao);
}
