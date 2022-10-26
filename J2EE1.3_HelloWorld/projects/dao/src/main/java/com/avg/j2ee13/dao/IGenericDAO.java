package com.avg.j2ee13.dao;

import com.avg.j2ee13.dto.BaseDTO;

import java.util.List;

public interface IGenericDAO {

    public abstract BaseDTO insert(BaseDTO baseDTO) throws DAOException;
    public abstract void update(BaseDTO baseDTO) throws DAOException;

    public abstract void delete(BaseDTO baseDTO) throws DAOException;
    public abstract List findAll() throws DAOException;
    public abstract BaseDTO findById(long id) throws DAOException;
}
