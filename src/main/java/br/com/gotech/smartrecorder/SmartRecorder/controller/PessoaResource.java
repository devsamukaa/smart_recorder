package br.com.gotech.smartrecorder.SmartRecorder.controller;

import br.com.gotech.smartrecorder.SmartRecorder.entity.PessoaEntity;
import br.com.gotech.smartrecorder.SmartRecorder.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<PessoaEntity> listar(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public PessoaEntity buscar(@PathVariable Long codigo){
        return pessoaRepository.findById(codigo).get();
    }

    @GetMapping("/{cpf}")
    public PessoaEntity buscarCpf(@PathVariable Long cpf){
        return pessoaRepository.findByCpf(cpf);
    }

    @GetMapping("/{cnpj}")
    public PessoaEntity buscarCnpj(@PathVariable Long cnpj){
        return pessoaRepository.findByCnpj(cnpj);
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
