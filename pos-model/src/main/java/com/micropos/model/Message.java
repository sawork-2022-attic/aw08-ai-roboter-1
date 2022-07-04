package com.micropos.model;

import java.io.Serializable;


public class Message implements Serializable {

    private String message;
    private Boolean success;

    public Message(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
}
