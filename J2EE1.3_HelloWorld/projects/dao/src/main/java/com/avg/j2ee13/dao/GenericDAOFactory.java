package com.avg.j2ee13.dao;

import com.avg.j2ee13.dao.database.DatabaseDAOFactory;
import com.avg.j2ee13.dao.filestore.FileDAOFactory;
import com.avg.j2ee13.dao.memory.MemoryDAOFactory;

import java.util.HashMap;

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

    public IGenericDAO getDAO(final int factory, final Class clazz, final HashMap parameters) throws DAOException {
        IGenericDAO dao = null;
        switch (factory) {
            case DAOParameters.LDAP:
                throw new DAOException(DAOException.ERROR_DAO_01, "Not implemented");
            case DAOParameters.ORACLE:
                return DatabaseDAOFactory.getInstance().getDAO(clazz, parameters);
            case DAOParameters.MEMORY:
                return MemoryDAOFactory.getInstance().getDAO(clazz, parameters);
            case DAOParameters.FILE_STORE:
                return FileDAOFactory.getInstance().getDAO(clazz, parameters);
        }
        throw new DAOException(DAOException.ERROR_DAO_01, "Not implemented");
    }
}
