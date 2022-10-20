package com.avg.j2ee13.dao.memory.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.memory.MemoryBaseDAO;
import com.avg.j2ee13.dto.BaseDTO;

import java.util.HashMap;
import java.util.List;

public class HelloWorldMemoryDAO extends MemoryBaseDAO {

    public HelloWorldMemoryDAO(HashMap parameters) {
        super(parameters);
    }

    public BaseDTO insert(BaseDTO baseDTO) throws DAOException {
        getData().add(baseDTO);
        return baseDTO;
    }

    public void update(BaseDTO baseDTO) throws DAOException {

    }

    public List findAll() throws DAOException {
        return getData();
    }

    public BaseDTO findById(long id) throws DAOException {
        return (BaseDTO) getData().get((int) id);
    }
}
