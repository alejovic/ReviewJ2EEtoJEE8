package com.avg.j2ee13.dto;

import java.util.Date;

public class HelloDTO extends BaseDTO {

    private String message;
    private Date dateOfCreation;

    public HelloDTO() {
    }

    public HelloDTO(long id, String message, Date dateOfCreation) {
        setId(id);
        this.message = message;
        this.dateOfCreation = dateOfCreation;
    }

    public HelloDTO(String message) {
        this.message = message;
    }

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

    public String toString() {
        return "HelloDTO{" +
                "id='" + getId() + '\'' +
                "message='" + message + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
