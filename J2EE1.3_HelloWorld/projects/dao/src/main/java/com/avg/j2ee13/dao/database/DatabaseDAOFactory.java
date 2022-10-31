package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for Database
 */
public final class DatabaseDAOFactory extends GenericDAOFactory {

    public IGenericDAO createDAO(Class clazz) throws DAOException {
        return createGenericDAO(DatabaseDAO.class, clazz);
    }


}
