package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.MedicaoFaseEntity;
import br.com.gotech.smartrecorder.repository.MedicaoFaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicao_fase")
public class MedicaoFaseResource {

    @Autowired
    private MedicaoFaseRepository medicaoFaseRepository;

    @GetMapping
    public List<MedicaoFaseEntity> listar(){ return medicaoFaseRepository.findAll();}

    @GetMapping("/{codigo}")
    public MedicaoFaseEntity buscar(@PathVariable Long codigo){
        return medicaoFaseRepository.findById(codigo).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MedicaoFaseEntity cadastrar(@RequestBody MedicaoFaseEntity medicaoFase){
        return medicaoFaseRepository.save(medicaoFase);
    }

    @PutMapping("/{id}")
    public MedicaoFaseEntity atualizar(@RequestBody MedicaoFaseEntity medicaoFase, @PathVariable Long id){
        medicaoFase.setCdMedicaoFase(id);
        return medicaoFaseRepository.save(medicaoFase);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo){ medicaoFaseRepository.deleteById(codigo);}

}
