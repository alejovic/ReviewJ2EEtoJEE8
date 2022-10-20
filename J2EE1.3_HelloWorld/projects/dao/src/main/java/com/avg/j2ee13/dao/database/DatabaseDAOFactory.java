package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.IGenericDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class DatabaseDAOFactory {

    protected Log log = LogFactory.getLog(DatabaseDAOFactory.class);
    private final static String ERROR_DAO = "ERR_DAO";

    private static DatabaseDAOFactory instance;

    private DatabaseDAOFactory() {
    }

    public static DatabaseDAOFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseDAOFactory();
        }
        return instance;
    }

    public IGenericDAO getDAO(Class clazz, HashMap parameters) throws DAOException {
        DatabaseDAO dao = null;

        try {
            Class daoClass = Class.forName(clazz.getName(), true, Thread
                    .currentThread().getContextClassLoader());
            Class[] parameterTypes = new Class[]{HashMap.class};
            Constructor ctor = daoClass.getConstructor(parameterTypes);
            dao = (DatabaseDAO) ctor.newInstance(new Object[]{parameters});
        } catch (ClassNotFoundException e) {

            throw new DAOException(ERROR_DAO, clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new DAOException(ERROR_DAO, clazz.getName(), e);
        } catch (InstantiationException e) {
            throw new DAOException(ERROR_DAO, clazz.getName(), e);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return dao;
    }


}
