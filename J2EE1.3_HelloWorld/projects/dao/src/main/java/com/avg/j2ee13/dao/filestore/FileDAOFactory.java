package com.avg.j2ee13.dao.filestore;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for FileStorage
 */
public final class FileDAOFactory extends GenericDAOFactory {

    public IGenericDAO createDAO(Class clazz) throws DAOException {
        return createGenericDAO(FileBaseDAO.class, clazz);
    }


}
