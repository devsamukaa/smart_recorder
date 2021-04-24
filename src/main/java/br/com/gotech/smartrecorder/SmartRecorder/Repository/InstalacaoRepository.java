package br.com.gotech.smartrecorder.SmartRecorder.Repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstalacaoRepository extends JpaRepository<InstalacaoEntity, Long> {

    List<InstalacaoEntity> findByIdInstalacao(Long idInstalacao);
    List<InstalacaoEntity> findByCdPessoa(br.com.gotech.smartrecorder.SmartRecorder.entity.PessoaEntity cdPessoa);
    List<InstalacaoEntity> findByIdEndereco(br.com.gotech.smartrecorder.SmartRecorder.entity.EnderecoEntity idEndereco);

}
