package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.DAOClassList;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for MemoryStorage
 */
public final class MemoryDAOFactory extends GenericDAOFactory {

    private final static String DAO_PACKAGE_BASE = "com.avg.j2ee13.dao.memory.implementations.";
    private final static String DAO_SUFFIX_BASE = "MemoryDAO";

    public IGenericDAO createDAO(DAOClassList daoName) throws DAOException {
        final String daoClassLocation = DAO_PACKAGE_BASE + daoName + DAO_SUFFIX_BASE;
        return createGenericDAO(MemoryBaseDAO.class, daoClassLocation);
    }

}
