package com.avg.j2ee13.dao;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public abstract class GenericAbstractDAO implements IGenericDAO {

    protected static final Log logger = LogFactory.getLog(GenericAbstractDAO.class);

    protected ServiceLocator locator;

    protected GenericAbstractDAO(final Map parameters) throws DAOException {
        if (parameters.get(DAOParameters.PARAM_SERVICE_LOCATOR) == null) {
            throw new DAOException(DAOException.DAO_MISSING_PARAMETER, "Parameter missing -> " + DAOParameters.PARAM_SERVICE_LOCATOR);
        }
        locator = (ServiceLocator) parameters.get(DAOParameters.PARAM_SERVICE_LOCATOR);
        initParameters(parameters);
    }

    protected abstract void initParameters(final Map parameters) throws DAOException;

}
