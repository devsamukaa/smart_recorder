package br.com.gotech.smartrecorder.dto;

import java.io.Serializable;

public class ResponseRedefinirSenha implements Serializable {

    private int status;
    private String nome;

    public ResponseRedefinirSenha() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
