package com.avg.j2ee13.dao;

import com.avg.j2ee13.dao.database.DatabaseDAOFactory;
import com.avg.j2ee13.dao.filestore.FileDAOFactory;
import com.avg.j2ee13.dao.memory.MemoryDAOFactory;

import java.util.Map;

public class GenericDAOFactory {

    private static GenericDAOFactory instance;

    private GenericDAOFactory() {
    }

    public static GenericDAOFactory getInstance() {
        if (instance == null) {
            instance = new GenericDAOFactory();
        }
        return instance;
    }

    public IGenericDAO getDAO(final int factory, final Class clazz, final Map parameters) throws DAOException {
        switch (factory) {
            case DAOParameters.FACTORY_ORACLE:
                return DatabaseDAOFactory.getInstance().getDAO(clazz, parameters);
            case DAOParameters.FACTORY_MEMORY:
                return MemoryDAOFactory.getInstance().getDAO(clazz, parameters);
            case DAOParameters.FACTORY_FILE_STORE:
                return FileDAOFactory.getInstance().getDAO(clazz, parameters);
            case DAOParameters.FACTORY_LDAP:
            default:
                throw new DAOException(DAOException.DAO_FACTORY_CLASS, "Not implemented");
        }
    }
}
