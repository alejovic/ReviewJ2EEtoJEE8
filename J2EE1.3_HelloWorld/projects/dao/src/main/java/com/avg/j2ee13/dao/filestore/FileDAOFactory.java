package com.avg.j2ee13.dao.filestore;

import com.avg.j2ee13.dao.DAOClassList;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for FileStorage
 */
public final class FileDAOFactory extends GenericDAOFactory {

    private final static String DAO_PACKAGE_BASE = "com.avg.j2ee13.dao.filestore.implementations.";
    private final static String DAO_SUFFIX_BASE = "FileDAO";

    public IGenericDAO createDAO(DAOClassList daoName) throws DAOException {
        final String daoClassLocation = DAO_PACKAGE_BASE + daoName + DAO_SUFFIX_BASE;
        return createGenericDAO(FileBaseDAO.class, daoClassLocation);
    }

}
