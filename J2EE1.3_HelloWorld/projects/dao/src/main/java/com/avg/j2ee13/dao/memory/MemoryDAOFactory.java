package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.DAOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Simple Factory.
 * <p>
 * Factory Maker for MemoryStorage
 */
public class MemoryDAOFactory {

    protected Log log = LogFactory.getLog(MemoryDAOFactory.class);

    private static MemoryDAOFactory instance;

    private MemoryDAOFactory() {
    }

    public static MemoryDAOFactory getInstance() {
        if (instance == null) {
            instance = new MemoryDAOFactory();
        }
        return instance;
    }

    public MemoryBaseDAO getDAO(Class clazz, Map parameters) throws DAOException {
        MemoryBaseDAO dao;
        try {
            Class daoClass = Class.forName(clazz.getName(), true, Thread
                    .currentThread().getContextClassLoader());
            Class[] parameterTypes = new Class[]{Map.class};
            Constructor ctor = daoClass.getConstructor(parameterTypes);
            dao = (MemoryBaseDAO) ctor.newInstance(new Object[]{parameters});
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
