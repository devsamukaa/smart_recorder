package br.com.gotech.smartrecorder.dto;

import java.io.Serializable;

public class AtualizarPerfilDto implements Serializable {

    private Long cdPessoa;
    private String nome;
    private String cpf;
    private String email;

    public AtualizarPerfilDto() {
    }

    public AtualizarPerfilDto(Long cdPessoa, String nome, String cpf, String email) {
        this.cdPessoa = cdPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(Long cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
