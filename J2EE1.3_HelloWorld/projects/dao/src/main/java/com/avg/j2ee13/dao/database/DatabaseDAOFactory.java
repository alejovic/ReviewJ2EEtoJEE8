package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOClassList;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for Database
 */
public final class DatabaseDAOFactory extends GenericDAOFactory {

    private final static String DAO_PACKAGE_BASE = "com.avg.j2ee13.dao.database.implementations.";
    private final static String DAO_SUFFIX_BASE = "DatabaseDAO";

    public IGenericDAO createDAO(DAOClassList daoName) throws DAOException {
        final String daoClassLocation = DAO_PACKAGE_BASE + daoName + DAO_SUFFIX_BASE;
        return createGenericDAO(DatabaseDAO.class, daoClassLocation);
    }

}
