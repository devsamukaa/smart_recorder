package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.MedicaoFaseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MedicaoFaseRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<MedicaoFaseEntity> findOrderedBySeatNumberLimitedTo(int limit) {
        return entityManager.createQuery("select mf from tb_medicao_fase mf order by mf.cdMedicaoFase desc", MedicaoFaseEntity.class).setMaxResults(limit).getResultList();
    }

}
