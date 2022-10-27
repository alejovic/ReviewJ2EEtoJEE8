package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.IGenericDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class MemoryDAOFactory {

    protected Log log = LogFactory.getLog(MemoryDAOFactory.class);
    private static final String ERROR_DAO = "ERR_DAO";

    private static MemoryDAOFactory instance;

    private MemoryDAOFactory() {
    }

    public static MemoryDAOFactory getInstance() {
        if (instance == null) {
            instance = new MemoryDAOFactory();
        }
        return instance;
    }

    public IGenericDAO getDAO(Class clazz, Map parameters) throws DAOException {
        MemoryBaseDAO dao = null;

        try {
            Class daoClass = Class.forName(clazz.getName(), true, Thread
                    .currentThread().getContextClassLoader());
            Class[] parameterTypes = new Class[]{HashMap.class};
            Constructor ctor = daoClass.getConstructor(parameterTypes);
            dao = (MemoryBaseDAO) ctor.newInstance(new Object[]{parameters});
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
