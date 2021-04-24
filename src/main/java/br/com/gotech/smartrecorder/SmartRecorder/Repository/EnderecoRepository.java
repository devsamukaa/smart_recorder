package br.com.gotech.smartrecorder.SmartRecorder.Repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    List<EnderecoEntity> findByIdEndereco(Long idEndereco);
    List<EnderecoEntity> findByCep(Long cep);
    List<EnderecoEntity> findByIdInstalacao(br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity idInstalacao);
}
