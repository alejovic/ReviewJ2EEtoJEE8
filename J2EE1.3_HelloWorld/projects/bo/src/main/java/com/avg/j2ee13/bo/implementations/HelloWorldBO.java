package com.avg.j2ee13.bo.implementations;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.dao.DAOClassList;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;
import com.avg.j2ee13.dto.HelloDTO;

import java.util.Date;

public class HelloWorldBO {

    private GenericDAOFactory daoSourceFactory;

    public HelloWorldBO(GenericDAOFactory daoSourceFactory) {
        this.daoSourceFactory = daoSourceFactory;
    }

    public HelloDTO storeMessage(String message) throws BOException {
        HelloDTO dto = new HelloDTO();
        dto.setMessage(message);
        dto.setDateOfCreation(new Date());
        try {
            IGenericDAO dao = this.daoSourceFactory.createDAO(DAOClassList.HelloWorld);
            dao.insert(dto);
        } catch (DAOException e) {
            throw new BOException(BOException.BO_ERROR_01, e.getMessage(), e);
        }
        return dto;
    }

    public HelloDTO getMessage(long id) throws BOException {
        HelloDTO dto = null;
        try {
            IGenericDAO dao = this.daoSourceFactory.createDAO(DAOClassList.HelloWorld);
            dto = (HelloDTO) dao.findById(id);
        } catch (DAOException e) {
            throw new BOException(BOException.BO_ERROR_01, e.getMessage(), e);
        }
        return dto;
    }
}
