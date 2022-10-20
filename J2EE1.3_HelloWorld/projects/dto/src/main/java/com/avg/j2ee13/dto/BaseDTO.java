package com.avg.j2ee13.dto;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

    private long id;

    protected long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }
}
