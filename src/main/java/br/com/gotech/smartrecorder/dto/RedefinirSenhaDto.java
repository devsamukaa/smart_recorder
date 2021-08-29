package br.com.gotech.smartrecorder.dto;

import java.io.Serializable;

public class RedefinirSenhaDto implements Serializable {

    private Long cd;
    private String id;
    private String password;

    public RedefinirSenhaDto() {
    }

    public Long getCd() {
        return cd;
    }

    public void setCd(Long cd) {
        this.cd = cd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
