package br.com.gotech.smartrecorder.repository;

import br.com.gotech.smartrecorder.dto.*;
import br.com.gotech.smartrecorder.entity.*;
import br.com.gotech.smartrecorder.entity.business.BusinessPessoaAutenticada;
import br.com.gotech.smartrecorder.entity.enum_classes.IdentificadorFase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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

    @Autowired
    private JavaMailSender emailSender;

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

    @Transactional
    public BusinessPessoaAutenticada atualizarPerfil(AtualizarPerfilDto pessoa) {

        PessoaEntity pessoaCompleta = pessoaRepository.findById(pessoa.getCdPessoa()).get();

        if(pessoaCompleta == null) {
            return null;
        }

        pessoaCompleta.setNome(pessoa.getNome());
        pessoaCompleta.setCpf(pessoa.getCpf());
        pessoaCompleta.setEmail(pessoa.getEmail());
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

    public ResponseDefault enviarEsqueceuSenha (EsqueceuSenhaDto esqueceuSenhaDto) {

        ResponseDefault responseDefault = new ResponseDefault();
        PessoaEntity pessoaEntity = pessoaRepository.findByEmail(esqueceuSenhaDto.getEmail());

        if(pessoaEntity == null) {
            return null;
        }

        String to = esqueceuSenhaDto.getEmail();
        String subject = "GoTech - E-mail de redefinição da Senha";

        String format = "Olá %s,\n\n"
                + "Acesse o link abaixo para realizar a alteração da sua senha:\n\n"
                + "https://google.com.br/redefinir_senha?id=%s\n\n"
                + "Conte conosco!\n\n"
                + "Abraços,\n"
                + "Time GoTech.";

        String text = String.format(
                format,
                pessoaEntity.getNome().split(" ")[0],
                pessoaEntity.getPassword());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gotech.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        responseDefault.setStatus(0);
        responseDefault.setMessage("E-mail enviado com sucesso!");

        try {
            emailSender.send(message);
        } catch (Exception e) {
            responseDefault.setStatus(-1);
            responseDefault.setMessage("Erro ao enviar mensagem");
        }

        return responseDefault;
    }

    @Transactional
    public ResponseRedefinirSenha redefinirSenha(RedefinirSenhaDto redefinirSenhaDto) {

        ResponseRedefinirSenha response = new ResponseRedefinirSenha();
        PessoaEntity pessoaEntity = pessoaRepository.findByCdPessoaAndPassword(redefinirSenhaDto.getCd(), redefinirSenhaDto.getId());

        if(pessoaEntity == null) {
            return null;
        }

        pessoaEntity.setPassword(redefinirSenhaDto.getPassword());
        response.setNome(pessoaEntity.getNome());
        response.setStatus(0);

        try{
            pessoaRepository.save(pessoaEntity);
        }catch (Exception e) {
            response.setStatus(-1);
        }

        return response;
    }

}
