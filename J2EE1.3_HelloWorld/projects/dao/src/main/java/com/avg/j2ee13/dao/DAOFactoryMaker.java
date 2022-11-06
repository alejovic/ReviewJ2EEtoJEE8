package com.avg.j2ee13.dao;

import com.avg.j2ee13.dao.database.DatabaseDAOFactory;
import com.avg.j2ee13.dao.filestore.FileDAOFactory;
import com.avg.j2ee13.dao.memory.MemoryDAOFactory;
import com.avg.j2ee13.util.localization.LocalizationException;
import com.avg.j2ee13.util.localization.ServiceLocator;

/**
 * Simple Factory
 * <p>
 * DAO Factory Maker
 */
public class DAOFactoryMaker {

    public static final int FACTORY_LDAP = 1;
    public static final int FACTORY_BD = 2;
    public static final int FACTORY_MEMORY = 3;
    public static final int FACTORY_FILE_STORE = 4;

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
        final int defaultFactory;
        try {
            defaultFactory = ServiceLocator.getInstance().getDAOFactory();
        } catch (LocalizationException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, e.getMessage(), e);
        }
        return createDAOFactory(defaultFactory);
    }

    public GenericDAOFactory createDAOFactory(final int factory) throws DAOException {
        switch (factory) {
            case FACTORY_BD:
                return new DatabaseDAOFactory();
            case FACTORY_MEMORY:
                return new MemoryDAOFactory();
            case FACTORY_FILE_STORE:
                return new FileDAOFactory();
            case FACTORY_LDAP:
            default:
                throw new DAOException(DAOException.DAO_FACTORY_CLASS, "Not implemented");
        }
    }

}
