package com.avg.j2ee13.dto;

import java.util.Date;

public class HelloDTO extends BaseDTO {

    public HelloDTO() {
    }

    public HelloDTO(String message) {
        this.message = message;
    }

    private String message;
    private Date dateOfCreation;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
