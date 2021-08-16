package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.entity.MedicaoFaseEntity;
import br.com.gotech.smartrecorder.repository.MedicaoFaseRepository;
import br.com.gotech.smartrecorder.repository.MedicaoFaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicao_fase")
public class MedicaoFaseResource {

    @Autowired
    private MedicaoFaseRepository medicaoFaseRepository;

    @Autowired
    private MedicaoFaseRepositoryImpl medicaoFaseRepositoryImpl;

    @GetMapping
    public List<MedicaoFaseEntity> listar(){ return (List<MedicaoFaseEntity>) medicaoFaseRepositoryImpl.findOrderedBySeatNumberLimitedTo(30); }

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
