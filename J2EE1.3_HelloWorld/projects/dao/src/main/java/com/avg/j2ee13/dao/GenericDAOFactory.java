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

    public abstract IGenericDAO createDAO(final DAOClassList daoName) throws DAOException;

    protected IGenericDAO createGenericDAO(Class daoType, String daoLocation) throws DAOException {
        try {
            final Class daoImplementation = Class.forName(daoLocation, true, Thread
                    .currentThread().getContextClassLoader());
            if (!daoType.isAssignableFrom(daoImplementation)) {
                throw new DAOException(DAOException.DAO_INSTANCE_CLASS, "The class " + daoImplementation + " does not belong to the family -> " + daoType);
            }
            final ServiceLocator locator = ServiceLocator.getInstance();

            final Class[] parameterTypes = new Class[]{ServiceLocator.class};
            final Constructor ctor = daoImplementation.getConstructor(parameterTypes);
            return (IGenericDAO) ctor.newInstance(new Object[]{locator});
        } catch (ClassNotFoundException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoLocation, e);
        } catch (IllegalAccessException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoLocation, e);
        } catch (InstantiationException e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoLocation, e);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_INSTANCE_CLASS, daoLocation, e);
        }
    }

}
