package com.avg.j2ee13.dao;

import com.avg.j2ee13.dao.database.DatabaseDAOFactory;
import com.avg.j2ee13.dao.filestore.FileDAOFactory;
import com.avg.j2ee13.dao.memory.MemoryDAOFactory;
import com.avg.j2ee13.util.localization.LocalizationException;
import com.avg.j2ee13.util.localization.ServiceLocator;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * DAO Factory Maker
 */
public class DAOFactoryMaker {

    private static DAOFactoryMaker instance;

    private DAOFactoryMaker() {
    }

    public static DAOFactoryMaker getInstance() {
        if (instance == null) {
            instance = new DAOFactoryMaker();
        }
        return instance;
    }

    public GenericDAOFactory createDefaultDAOFactory() throws DAOException {
        final int factory;
        try {
            factory = ServiceLocator.getInstance().getDAOFactory();
        } catch (LocalizationException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, e.getMessage(), e);
        }
        return createDAOFactory(factory);
    }

    public GenericDAOFactory createDAOFactory(final int factory) throws DAOException {
        switch (factory) {
            case DAOParameters.FACTORY_BD:
                return new DatabaseDAOFactory();
            case DAOParameters.FACTORY_MEMORY:
                return new MemoryDAOFactory();
            case DAOParameters.FACTORY_FILE_STORE:
                return new FileDAOFactory();
            case DAOParameters.FACTORY_LDAP:
            default:
                throw new DAOException(DAOException.DAO_FACTORY_CLASS, "Not implemented");
        }
    }

}
