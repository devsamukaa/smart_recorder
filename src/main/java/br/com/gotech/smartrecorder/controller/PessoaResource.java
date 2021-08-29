package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.dto.AlterarSenhaDto;
import br.com.gotech.smartrecorder.dto.AtualizarPerfilDto;
import br.com.gotech.smartrecorder.entity.PessoaEntity;
import br.com.gotech.smartrecorder.entity.business.BusinessPessoaAutenticada;
import br.com.gotech.smartrecorder.repository.PessoaRepository;
import br.com.gotech.smartrecorder.repository.PessoaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    PessoaRepositoryImpl pessoaRepositoryImpl;

    @GetMapping
    public List<PessoaEntity> listar(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public PessoaEntity buscar(@PathVariable Long codigo){
        return pessoaRepository.findById(codigo).get();
    }

    @PostMapping("/login")
    public BusinessPessoaAutenticada login(@RequestBody PessoaEntity pessoaEntity) {

        BusinessPessoaAutenticada pessoaAutenticada = pessoaRepositoryImpl.infosByEmailAndPassword(pessoaEntity.getEmail(), pessoaEntity.getPassword());

        if(pessoaAutenticada == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário ou senha inválidos");
        }

        return pessoaAutenticada;
    }

    @PostMapping("/alterar_senha")
    public BusinessPessoaAutenticada alterarSenha(@RequestBody AlterarSenhaDto pessoa) {

        BusinessPessoaAutenticada pessoaAutenticada = pessoaRepositoryImpl.alterarSenha(pessoa);

        if(pessoaAutenticada == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Senha atual inválida");
        }

        return pessoaAutenticada;
    }

    @PostMapping("/atualizar_perfil")
    public BusinessPessoaAutenticada atualizarPerfil(@RequestBody AtualizarPerfilDto pessoa) {

        BusinessPessoaAutenticada pessoaAutenticada = pessoaRepositoryImpl.atualizarPerfil(pessoa);

        if(pessoaAutenticada == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro");
        }

        return pessoaAutenticada;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public BusinessPessoaAutenticada cadastrarApp(@RequestBody PessoaEntity pessoaEntity) {

        BusinessPessoaAutenticada pessoaAutenticada = null;

        try{
            pessoaAutenticada = pessoaRepositoryImpl.cadastrar(pessoaEntity);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro durante o cadastro");
        }

        return pessoaAutenticada;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PessoaEntity cadastrar(@RequestBody PessoaEntity pessoa){
        return  pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public PessoaEntity atualizar(@RequestBody PessoaEntity pessoa, @PathVariable Long id){
        pessoa.setCdPessoa(id);
        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

}
