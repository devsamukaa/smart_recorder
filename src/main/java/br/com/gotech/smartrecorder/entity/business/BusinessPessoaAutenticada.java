package br.com.gotech.smartrecorder.entity.business;

import br.com.gotech.smartrecorder.entity.ContaLuzEntity;
import br.com.gotech.smartrecorder.entity.FaseEntity;
import br.com.gotech.smartrecorder.entity.InstalacaoEntity;
import br.com.gotech.smartrecorder.entity.PessoaEntity;
import java.util.List;

public class BusinessPessoaAutenticada extends PessoaEntity {

    private ContaLuzEntity contaLuz;
    private InstalacaoEntity instalacao;

    public BusinessPessoaAutenticada(PessoaEntity pessoa) {
        super(pessoa);
    }

    public ContaLuzEntity getContaLuz() {
        return contaLuz;
    }

    public void setContaLuz(ContaLuzEntity contaLuz) {
        this.contaLuz = contaLuz;
    }

    public InstalacaoEntity getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(InstalacaoEntity instalacao) {
        this.instalacao = instalacao;
    }

}
