package com.example.tutorial.response;

public class ApiResponse <T>{
    private boolean error;
    private String status;
    private String message;
    private T data;

    public ApiResponse(boolean error, String status, String message, T data) {
        this.error = error;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*T means the data can contain different types.*/
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
