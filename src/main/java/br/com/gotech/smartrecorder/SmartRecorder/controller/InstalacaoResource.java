package br.com.gotech.smartrecorder.SmartRecorder.controller;

import br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity;
import br.com.gotech.smartrecorder.SmartRecorder.repository.InstalacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class InstalacaoResource {
    @Autowired
    private InstalacaoRepository instalacaoRepository;

    @GetMapping
    public List<InstalacaoEntity> listar(){
        return instalacaoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public InstalacaoEntity buscar(@PathVariable Long codigo){
        return instalacaoRepository.findById(codigo).get();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InstalacaoEntity cadastrar(@RequestBody InstalacaoEntity instalacao, @PathVariable Long id){
        instalacao.setIdInstalacao(id);
        return instalacaoRepository.save(instalacao);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo) {
        instalacaoRepository.deleteById(codigo);
    }

}
