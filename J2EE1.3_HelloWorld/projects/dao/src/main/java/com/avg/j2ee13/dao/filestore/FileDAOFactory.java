package com.avg.j2ee13.dao.filestore;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.IGenericDAO;
import com.avg.j2ee13.dao.memory.MemoryBaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class FileDAOFactory {

    protected Log log = LogFactory.getLog(FileDAOFactory.class);
    private  static final String ERROR_DAO = "ERR_DAO";

    private static FileDAOFactory instance;

    private FileDAOFactory() {
    }

    public static FileDAOFactory getInstance() {
        if (instance == null) {
            instance = new FileDAOFactory();
        }
        return instance;
    }

    public IGenericDAO getDAO(Class clazz, HashMap parameters) throws DAOException {
        FileBaseDAO dao = null;

        try {
            Class daoClass = Class.forName(clazz.getName(), true, Thread
                    .currentThread().getContextClassLoader());
            Class[] parameterTypes = new Class[]{HashMap.class};
            Constructor ctor = daoClass.getConstructor(parameterTypes);
            dao = (FileBaseDAO) ctor.newInstance(new Object[]{parameters});
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
