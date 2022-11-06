package com.avg.j2ee13.bo;

import com.avg.j2ee13.dao.DAOClassList;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;
import com.avg.j2ee13.dto.HelloDTO;

public class IHelloWorldBusinessImpl {

    private GenericDAOFactory daoSourceFactory;

    public IHelloWorldBusinessImpl(GenericDAOFactory daoSourceFactory) {
        this.daoSourceFactory = daoSourceFactory;
    }

    public HelloDTO storeMessage(String message) throws BOException {
        HelloDTO dto = new HelloDTO();
        dto.setMessage(message);
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
