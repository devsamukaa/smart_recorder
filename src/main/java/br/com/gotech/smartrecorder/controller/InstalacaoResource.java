package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.EnderecoEntity;
import br.com.gotech.smartrecorder.entity.InstalacaoEntity;
import br.com.gotech.smartrecorder.repository.InstalacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instalacao")
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
    public InstalacaoEntity cadastrar(@RequestBody InstalacaoEntity instalacao){
        return instalacaoRepository.save(instalacao);
    }

    @PutMapping("/{codigo}")
    public InstalacaoEntity atualizar (@RequestBody InstalacaoEntity instalacao, @PathVariable Long codigo){
        instalacao.setCdInstalacao(codigo);
        return instalacaoRepository.save(instalacao);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo) {
        instalacaoRepository.deleteById(codigo);
    }

}
