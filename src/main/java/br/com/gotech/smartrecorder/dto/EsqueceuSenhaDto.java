package br.com.gotech.smartrecorder.dto;

import java.io.Serializable;

public class EsqueceuSenhaDto implements Serializable {
    String email;

    public EsqueceuSenhaDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
