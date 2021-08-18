package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.InstalacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalacaoRepository extends JpaRepository<InstalacaoEntity, Long> {

    InstalacaoEntity getByPessoa_CdPessoa(Long cdPessoa);

}
