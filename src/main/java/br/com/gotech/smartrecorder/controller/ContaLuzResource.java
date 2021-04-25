package br.com.gotech.smartrecorder.controller;

import br.com.gotech.smartrecorder.repository.ContaLuzRepository;
import br.com.gotech.smartrecorder.entity.ContaLuzEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta_luz")
public class ContaLuzResource {

    @Autowired
    private ContaLuzRepository contaLuzRepository;

    @GetMapping
    public List<ContaLuzEntity> listar() { return contaLuzRepository.findAll();}

    @GetMapping("/{codigo}")
    public ContaLuzEntity buscar(@PathVariable Long codigo){
        return contaLuzRepository.findById(codigo).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContaLuzEntity cadastrar(@RequestBody ContaLuzEntity conta){
        return contaLuzRepository.save(conta);
    }

    @PutMapping("/{id}")
    public ContaLuzEntity atualizar(@RequestBody ContaLuzEntity conta, @PathVariable Long id){
        conta.setCdContaLuz(id);
        return contaLuzRepository.save(conta);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo){ contaLuzRepository.deleteById(codigo);}

}
