package br.com.gotech.smartrecorder.SmartRecorder.repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.EnderecoEntity;
import br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity;
import br.com.gotech.smartrecorder.SmartRecorder.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstalacaoRepository extends JpaRepository<InstalacaoEntity, Long> {

    List<InstalacaoEntity> findByIdInstalacao(Long idInstalacao);
    List<InstalacaoEntity> findByCdPessoa(PessoaEntity cdPessoa);
    List<InstalacaoEntity> findByIdEndereco(EnderecoEntity idEndereco);

}
