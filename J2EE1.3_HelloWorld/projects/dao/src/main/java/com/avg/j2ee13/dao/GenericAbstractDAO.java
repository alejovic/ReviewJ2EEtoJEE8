package com.avg.j2ee13.dao;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public abstract class GenericAbstractDAO implements IGenericDAO {

    protected static final Log logger = LogFactory.getLog(GenericAbstractDAO.class);

    private Map parameters;
    protected ServiceLocator locator;

    protected GenericAbstractDAO(Map parameters) {
        this.parameters = parameters;
        locator = (ServiceLocator) parameters.get("SERVICE_LOCATOR");
    }

    public Map getParameters() {
        return parameters;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }
}
