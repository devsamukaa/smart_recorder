package br.com.gotech.smartrecorder.SmartRecorder.repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.EnderecoEntity;
import br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    List<EnderecoEntity> findByIdEndereco(Long idEndereco);
    List<EnderecoEntity> findByCep(Long cep);
    List<EnderecoEntity> findByIdInstalacao(InstalacaoEntity idInstalacao);
}
