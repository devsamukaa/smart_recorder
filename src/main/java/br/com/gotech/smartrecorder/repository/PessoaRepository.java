package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {


}