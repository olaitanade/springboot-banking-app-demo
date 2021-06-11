package com.example.bankapplication.model.response;

public class DefaultResponse {
    private int responseCode = 200;
    private boolean success = true;
    private String message = "Successful";

    public DefaultResponse(){}
    public DefaultResponse(String message){
        this.message = message;
    }
    public DefaultResponse(int responseCode, boolean success, String message){
        this.responseCode = responseCode;
        this.success = success;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
