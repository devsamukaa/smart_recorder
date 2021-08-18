package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.entity.MedicaoFaseEntity;
import br.com.gotech.smartrecorder.entity.PessoaEntity;
import br.com.gotech.smartrecorder.entity.business.BusinessPessoaAutenticada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PessoaRepositoryImpl {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaLuzRepository contaLuzRepository;

    @Autowired
    private InstalacaoRepository instalacaoRepository;

    public BusinessPessoaAutenticada infosByEmailAndPassword(String email, String password) {

        PessoaEntity pessoa = pessoaRepository.findByEmailAndPassword(email, password);

        if(pessoa == null) {
            return null;
        }

        BusinessPessoaAutenticada pessoaAutenticada = new BusinessPessoaAutenticada(pessoa);

        pessoaAutenticada.setInstalacao(
                instalacaoRepository.getByPessoa_CdPessoa(pessoaAutenticada.getCdPessoa())
        );

        if(pessoaAutenticada.getInstalacao() != null){
            pessoaAutenticada.setContaLuz(
                    contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(
                            pessoaAutenticada.getInstalacao().getCdInstalacao()
                    )
            );
        }

        return pessoaAutenticada;
    }

}
