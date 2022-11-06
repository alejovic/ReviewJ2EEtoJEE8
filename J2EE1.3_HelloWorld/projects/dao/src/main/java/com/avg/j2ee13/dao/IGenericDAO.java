package com.avg.j2ee13.dao;

import com.avg.j2ee13.dto.BaseDTO;

import java.util.List;

public interface IGenericDAO {

    BaseDTO insert(BaseDTO baseDTO) throws DAOException;

    void update(BaseDTO baseDTO) throws DAOException;

    void delete(BaseDTO baseDTO) throws DAOException;

    List findAll() throws DAOException;

    BaseDTO findById(long id) throws DAOException;
}
