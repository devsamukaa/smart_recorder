package br.com.gotech.smartrecorder.SmartRecorder.Repository;

import br.com.gotech.smartrecorder.SmartRecorder.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

    List<PessoaEntity> findByCdPessoa(Long cdPessoa);
    List<PessoaEntity> findByNomePessoa(String nomePessoa);
    List<PessoaEntity> findByCpf(Long cpf);
    List<PessoaEntity> findByCnpj(Long cnpj);
}
