package br.com.gotech.smartrecorder.SmartRecorder.controller;

import br.com.gotech.smartrecorder.SmartRecorder.repository.EnderecoRepository;
import br.com.gotech.smartrecorder.SmartRecorder.repository.InstalacaoRepository;
import br.com.gotech.smartrecorder.SmartRecorder.entity.EnderecoEntity;
import br.com.gotech.smartrecorder.SmartRecorder.entity.InstalacaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private InstalacaoRepository instalacaoRepository;

    @GetMapping
    public List<EnderecoEntity> listar() { return enderecoRepository.findAll();}

    @GetMapping("/{id}")
    public EnderecoEntity buscar(@PathVariable Long id){ return enderecoRepository.findById(id).get(); }

    @GetMapping("/{id_instalacao}")
    public InstalacaoEntity buscarInstalacao(@PathVariable Long idInstalacao){
        return instalacaoRepository.findById(idInstalacao).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EnderecoEntity cadastrar(@RequestBody EnderecoEntity endereco){ return enderecoRepository.save(endereco);}

    @PutMapping("/{codigo}")
    public EnderecoEntity atualizar (@RequestBody EnderecoEntity endereco, @PathVariable Long codigo){
        endereco.setIdEndereco(codigo);
        return enderecoRepository.save(endereco);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable Long codigo){ enderecoRepository.deleteById(codigo);}

}
