package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.BandeiraEntity;
import br.com.gotech.smartrecorder.entity.ContaLuzEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandeiraRepository extends JpaRepository<BandeiraEntity, Long> {

    BandeiraEntity findByIsBandeiraAtiva(boolean isBandeiraAtiva);

}
