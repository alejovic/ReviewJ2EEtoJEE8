package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Factory Maker for Database
 */
public final class DatabaseDAOFactory extends GenericDAOFactory {

    public IGenericDAO createDAO(Class clazz, Map parameters) throws DAOException {
        DatabaseDAO dao;
        try {
            if (!DatabaseDAO.class.isAssignableFrom(clazz)) {
                throw new DAOException(DAOException.DAO_INSTANCE_CLASS, "The class " + clazz + " does not belong to the family -> " + DatabaseDAO.class);
            }

            Class daoClass = Class.forName(clazz.getName(), true, Thread
                    .currentThread().getContextClassLoader());
            Class[] parameterTypes = new Class[]{Map.class};
            Constructor ctor = daoClass.getConstructor(parameterTypes);
            dao = (DatabaseDAO) ctor.newInstance(new Object[]{parameters});
        } catch (ClassNotFoundException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, clazz.getName(), e);
        } catch (InstantiationException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, clazz.getName(), e);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, clazz.getName(), e);
        }

        return dao;
    }


}
