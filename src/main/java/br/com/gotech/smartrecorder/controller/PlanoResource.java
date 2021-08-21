package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.PlanoEntity;
import br.com.gotech.smartrecorder.repository.PlanoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plano")
public class PlanoResource {
    @Autowired
    private PlanoRepository planoRepository;

    @GetMapping
    public List<PlanoEntity> listar(){
        return planoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public PlanoEntity buscar(@PathVariable Long codigo){
        return planoRepository.findById(codigo).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlanoEntity cadastrar(@RequestBody PlanoEntity plano){
        return planoRepository.save(plano);
    }

    @PutMapping("/{id}")
    public PlanoEntity atualizar(@RequestBody PlanoEntity plano, @PathVariable Long id){
        plano.setCdPlano(id);
        return planoRepository.save(plano);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo) {
        planoRepository.deleteById(codigo);
    }

}
