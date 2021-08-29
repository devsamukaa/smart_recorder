package br.com.gotech.smartrecorder.dto;

import java.io.Serializable;

public class ResponseDefault implements Serializable {

    private int status;
    private String message;

    public ResponseDefault() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
