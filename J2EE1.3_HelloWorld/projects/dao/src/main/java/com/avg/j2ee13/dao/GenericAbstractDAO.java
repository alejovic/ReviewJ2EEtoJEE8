package com.avg.j2ee13.dao;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;

public abstract class GenericAbstractDAO implements IGenericDAO {

    protected static final Log logger = LogFactory.getLog(GenericAbstractDAO.class);

    private HashMap parameters;
    protected ServiceLocator locator;

    public GenericAbstractDAO(HashMap parameters) {
        this.parameters = parameters;
        locator = (ServiceLocator) parameters.get("SERVICE_LOCATOR");
    }

    public HashMap getParameters() {
        return parameters;
    }

    public void setParameters(HashMap parameters) {
        this.parameters = parameters;
    }
}
