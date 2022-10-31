package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for MemoryStorage
 */
public final class MemoryDAOFactory extends GenericDAOFactory {

    public IGenericDAO createDAO(Class clazz) throws DAOException {
        return createGenericDAO(MemoryBaseDAO.class, clazz);
    }

}
