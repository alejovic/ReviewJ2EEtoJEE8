package com.avg.j2ee13.dao;

import com.avg.j2ee13.util.localization.ServiceLocator;

import java.lang.reflect.Constructor;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Generic DAO Factory
 */
public abstract class GenericDAOFactory {

    public GenericDAOFactory() {
    }

    public abstract IGenericDAO createDAO(final Class clazz) throws DAOException;

    protected IGenericDAO createGenericDAO(Class daoType, Class daoClass) throws DAOException {
        try {
            if (!daoType.isAssignableFrom(daoClass)) {
                throw new DAOException(DAOException.DAO_INSTANCE_CLASS, "The class " + daoClass + " does not belong to the family -> " + daoType);
            }
            final ServiceLocator locator = ServiceLocator.getInstance();

            final Class daoImplementation = Class.forName(daoClass.getName(), true, Thread
                    .currentThread().getContextClassLoader());

            final Class[] parameterTypes = new Class[]{ServiceLocator.class};
            final Constructor ctor = daoImplementation.getConstructor(parameterTypes);
            return (IGenericDAO) ctor.newInstance(new Object[]{locator});
        } catch (ClassNotFoundException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoClass.getName(), e);
        } catch (IllegalAccessException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoClass.getName(), e);
        } catch (InstantiationException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoClass.getName(), e);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoClass.getName(), e);
        }
    }

}
