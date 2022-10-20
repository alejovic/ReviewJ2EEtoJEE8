package com.avg.j2ee13.dao;

import com.avg.j2ee13.dao.database.DatabaseDAOFactory;
import com.avg.j2ee13.dao.memory.MemoryDAOFactory;

import java.util.HashMap;

public class GenericDAOFactory {

    public static final int LDAP = 1;
    public static final int ORACLE = 2;
    public static final int MEMORY = 3;

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
            case LDAP:
                throw new DAOException(DAOException.ERROR_DAO_01, "Not implemented");
            case ORACLE:
                return DatabaseDAOFactory.getInstance().getDAO(clazz, parameters);
            case MEMORY:
                return MemoryDAOFactory.getInstance().getDAO(clazz, parameters);
        }
        throw new DAOException(DAOException.ERROR_DAO_01, "Not implemented");
    }
}
