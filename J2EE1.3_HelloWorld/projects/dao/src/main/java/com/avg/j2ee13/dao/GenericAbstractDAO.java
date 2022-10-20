package com.avg.j2ee13.dao;

import java.util.HashMap;

public abstract class GenericAbstractDAO implements IGenericDAO {

    private HashMap parameters;

    public GenericAbstractDAO(HashMap parameters) {
        this.parameters = parameters;
    }

    public HashMap getParameters() {
        return parameters;
    }

    public void setParameters(HashMap parameters) {
        this.parameters = parameters;
    }
}
