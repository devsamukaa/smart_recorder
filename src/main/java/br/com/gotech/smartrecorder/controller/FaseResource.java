package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.FaseEntity;
import br.com.gotech.smartrecorder.entity.InstalacaoEntity;
import br.com.gotech.smartrecorder.repository.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fase")
public class FaseResource {

    @Autowired
    private FaseRepository faseRepository;

    @GetMapping
    public List<FaseEntity> listar(){ return faseRepository.findAll();}

    @GetMapping("/{id}")
    public FaseEntity buscar(@PathVariable Long id){
        return faseRepository.findById(id).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FaseEntity cadastrar(@RequestBody FaseEntity fase){
        return faseRepository.save(fase);
    }

    @PutMapping("/{codigo}")
    public FaseEntity atualizar (@RequestBody FaseEntity fase, @PathVariable Long codigo){
        fase.setCdFase(codigo);
        return faseRepository.save(fase);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo){ faseRepository.deleteById(codigo);}
}
