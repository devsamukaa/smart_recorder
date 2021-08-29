package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

    PessoaEntity findByEmailAndPassword(String email, String password);

    PessoaEntity findByEmail(String email);

    PessoaEntity findByCdPessoaAndPassword(Long cdPessoa, String password);

}
