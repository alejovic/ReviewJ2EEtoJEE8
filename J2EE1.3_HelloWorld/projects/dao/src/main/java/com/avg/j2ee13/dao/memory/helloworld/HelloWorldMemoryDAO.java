package com.avg.j2ee13.dao.memory.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.memory.MemoryBaseDAO;
import com.avg.j2ee13.dto.BaseDTO;
import com.avg.j2ee13.util.localization.ServiceLocator;

import java.util.List;

/**
 * Simple Factory -> Factory Method Design Pattern
 * MemoryDAO Concrete implementation
 */
public class HelloWorldMemoryDAO extends MemoryBaseDAO {


    protected HelloWorldMemoryDAO(ServiceLocator locator) {
        super(locator);
    }

    public BaseDTO insert(BaseDTO baseDTO) throws DAOException {
        try {
            getData().add(baseDTO);
            return baseDTO;
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_INSERT, e.getMessage(), e);
        }
    }

    public void update(BaseDTO baseDTO) throws DAOException {
        try {
            final int index = getIndex(baseDTO);
            getData().set(index, baseDTO);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_UPDATE, e.getMessage(), e);
        }
    }

    public void delete(BaseDTO baseDTO) throws DAOException {
        try {
            final int index = getIndex(baseDTO);
            getData().remove(index);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_DELETE, e.getMessage(), e);
        }
    }

    public List findAll() throws DAOException {
        try {
            return getData();
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_FIND_ALL, e.getMessage(), e);
        }
    }

    public BaseDTO findById(long id) throws DAOException {
        try {
            return (BaseDTO) getData().get((int) id);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_FIND, e.getMessage(), e);
        }
    }

    private int getIndex(BaseDTO baseDTO) {
        return getData().indexOf(baseDTO);
    }
}
