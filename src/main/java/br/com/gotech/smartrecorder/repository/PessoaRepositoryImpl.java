package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.dto.AlterarSenhaDto;
import br.com.gotech.smartrecorder.entity.*;
import br.com.gotech.smartrecorder.entity.business.BusinessPessoaAutenticada;
import br.com.gotech.smartrecorder.entity.enum_classes.IdentificadorFase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

public class PessoaRepositoryImpl {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaLuzRepository contaLuzRepository;

    @Autowired
    private InstalacaoRepository instalacaoRepository;

    @Autowired
    private FaseRepository faseRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TipoHabitacaoRepository tipoHabitacaoRepository;

    public BusinessPessoaAutenticada infosByEmailAndPassword(String email, String password) {

        PessoaEntity pessoa = pessoaRepository.findByEmailAndPassword(email, password);

        if(pessoa == null) {
            return null;
        }

        return getPessoaAutenticada(pessoa);
    }

    @Transactional
    public BusinessPessoaAutenticada cadastrar(PessoaEntity pessoa) {

        PessoaEntity pessoaEntity = pessoaRepository.save(pessoa);

        EnderecoEntity enderecoEntity = enderecoRepository.save(new EnderecoEntity(
                0l,
                "Endereco não definido",
                10l,
                "",
                "Bairro não definido",
                "Cidade não definida",
                "NA",
                "00000-000",
                0.0,
                0.0
        ));

        Optional<TipoHabitacaoEntity> tipoHabitacaoEntity = tipoHabitacaoRepository.findById(1l);

        InstalacaoEntity instalacaoEntity = instalacaoRepository.save(new InstalacaoEntity(
                0l,
                true,
                enderecoEntity,
                tipoHabitacaoEntity.get(),
                pessoaEntity
        ));

        FaseEntity faseEntity = faseRepository.save(new FaseEntity(
                0l,
                "FASE PADRÃO",
                IdentificadorFase.FASE,
                instalacaoEntity
        ));

        instalacaoEntity.setFases(new ArrayList<FaseEntity>());
        instalacaoEntity.getFases().add(faseEntity);

        BusinessPessoaAutenticada pessoaAutenticada = new BusinessPessoaAutenticada(pessoa);
        pessoaAutenticada.setInstalacao(instalacaoEntity);

        return pessoaAutenticada;

    }

    @Transactional
    public BusinessPessoaAutenticada alterarSenha(AlterarSenhaDto pessoa) {

        PessoaEntity pessoaCompleta = pessoaRepository.findByEmailAndPassword(pessoa.getEmail(), pessoa.getPassword());

        if(pessoaCompleta == null) {
            return null;
        }

        pessoaCompleta.setPassword(pessoa.getNewPassword());
        pessoaRepository.save(pessoaCompleta);

        return getPessoaAutenticada(pessoaCompleta);

    }

    private BusinessPessoaAutenticada getPessoaAutenticada(PessoaEntity pessoaCompleta) {
        BusinessPessoaAutenticada pessoaAutenticada = new BusinessPessoaAutenticada(pessoaCompleta);

        pessoaAutenticada.setInstalacao(
                instalacaoRepository.getByPessoa_CdPessoa(pessoaAutenticada.getCdPessoa())
        );

        if(pessoaAutenticada.getInstalacao() != null){
            pessoaAutenticada.setContaLuz(
                    contaLuzRepository.getByInstalacao_CdInstalacaoOrderByDataValidadeDesc(
                            pessoaAutenticada.getInstalacao().getCdInstalacao()
                    )
            );

            pessoaAutenticada.getInstalacao().setFases(
                    faseRepository.findByInstalacaoCdInstalacao(
                            pessoaAutenticada.getInstalacao().getCdInstalacao()
                    )
            );
        }

        return pessoaAutenticada;
    }

}
