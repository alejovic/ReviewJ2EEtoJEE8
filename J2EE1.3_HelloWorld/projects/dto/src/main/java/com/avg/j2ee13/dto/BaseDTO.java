package com.avg.j2ee13.dto;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseDTO baseDTO = (BaseDTO) o;

        return id == baseDTO.id;
    }

    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public String toString() {
        return "BaseDTO{" +
                "id=" + id +
                '}';
    }
}
