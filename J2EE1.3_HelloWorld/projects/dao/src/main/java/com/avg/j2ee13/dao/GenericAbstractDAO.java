package com.avg.j2ee13.dao;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class GenericAbstractDAO implements IGenericDAO {

    protected static final Log logger = LogFactory.getLog(GenericAbstractDAO.class);

    protected ServiceLocator locator;

    protected GenericAbstractDAO(ServiceLocator locator) {
        this.locator = locator;
    }

}
