package com.example.pvanjewelsdiamond.Api.ApiModel;

public class Message {
    private boolean Status;
    private String Message;

    public Message(boolean status, String message) {
        Status = status;
        this.Message = message;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
}
