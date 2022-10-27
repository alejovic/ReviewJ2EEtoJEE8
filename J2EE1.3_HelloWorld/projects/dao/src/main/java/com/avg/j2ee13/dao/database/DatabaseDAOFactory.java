package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.IGenericDAO;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DatabaseDAOFactory {

    private static DatabaseDAOFactory instance;

    private DatabaseDAOFactory() {
    }

    public static DatabaseDAOFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseDAOFactory();
        }
        return instance;
    }

    public IGenericDAO getDAO(Class clazz, Map parameters) throws DAOException {
        DatabaseDAO dao = null;

        try {
            Class daoClass = Class.forName(clazz.getName(), true, Thread
                    .currentThread().getContextClassLoader());
            Class[] parameterTypes = new Class[]{HashMap.class};
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
