package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.TipoHabitacaoEntity;
import br.com.gotech.smartrecorder.repository.TipoHabitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo_habitacao")
public class TipoHabitacaoResource {
    @Autowired
    private TipoHabitacaoRepository tipoHabitacaoRepository;

    @GetMapping
    public List<TipoHabitacaoEntity> listar(){
        return tipoHabitacaoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public TipoHabitacaoEntity buscar(@PathVariable Long codigo){
        return tipoHabitacaoRepository.findById(codigo).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TipoHabitacaoEntity cadastrar(@RequestBody TipoHabitacaoEntity tipoHabitacao){
        return tipoHabitacaoRepository.save(tipoHabitacao);
    }

    @PutMapping("/{id}")
    public TipoHabitacaoEntity atualizar(@RequestBody TipoHabitacaoEntity tipoHabitacao, @PathVariable Long id){
        tipoHabitacao.setCdTipoHabitacao(id);
        return tipoHabitacaoRepository.save(tipoHabitacao);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo) {
        tipoHabitacaoRepository.deleteById(codigo);
    }

}
