package com.avg.j2ee13.dto;

public class HelloDTO extends BaseDTO {

    public HelloDTO() {
    }

    public HelloDTO(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
