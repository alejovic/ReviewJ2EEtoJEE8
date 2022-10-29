package com.avg.j2ee13.dao;

import java.util.Map;

/**
 * Simple Factory -> Factory Method Design Pattern
 * <p>
 * Generic DAO Factory
 */
public abstract class GenericDAOFactory {

    public GenericDAOFactory() {
    }

    public abstract IGenericDAO createDAO(final Class clazz, final Map parameters) throws DAOException;

}
