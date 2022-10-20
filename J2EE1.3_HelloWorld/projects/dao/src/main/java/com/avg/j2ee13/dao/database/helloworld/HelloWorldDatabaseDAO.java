package com.avg.j2ee13.dao.database.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.database.DatabaseDAO;
import com.avg.j2ee13.dto.BaseDTO;

import java.util.HashMap;
import java.util.List;

public class HelloWorldDatabaseDAO extends DatabaseDAO {

    public HelloWorldDatabaseDAO(HashMap parameters) {
        super(parameters);
    }

    public BaseDTO insert(BaseDTO baseDTO) throws DAOException {
        return null;
    }

    public void update(BaseDTO baseDTO) throws DAOException {

    }

    public List findAll() throws DAOException {
        return null;
    }

    public BaseDTO findById(long id) throws DAOException {
        return null;
    }
}
