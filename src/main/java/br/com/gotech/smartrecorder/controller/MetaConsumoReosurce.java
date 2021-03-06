package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.MetaConsumoEntity;
import br.com.gotech.smartrecorder.repository.MetaConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meta_consumo")
public class MetaConsumoReosurce {
    @Autowired
    private MetaConsumoRepository metaConsumoRepository;

    @GetMapping
    public List<MetaConsumoEntity> listar(){
        return metaConsumoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public MetaConsumoEntity buscar(@PathVariable Long codigo) {
        return metaConsumoRepository.findById(codigo).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public  MetaConsumoEntity cadastrar(@RequestBody MetaConsumoEntity metaConsumo){

        MetaConsumoEntity metaConsumoEntity = metaConsumo;

        return metaConsumoRepository.save(metaConsumo);
    }

    @PutMapping("/{id}")
    public MetaConsumoEntity atualizar(@RequestBody MetaConsumoEntity metaConsumo, @PathVariable Long id){
        metaConsumo.setCdMetaConsumo(id);
        return metaConsumoRepository.save(metaConsumo);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo) {
        metaConsumoRepository.deleteById(codigo);
    }


}
