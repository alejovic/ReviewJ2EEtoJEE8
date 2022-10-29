package com.avg.j2ee13.bo;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;
import com.avg.j2ee13.dao.filestore.FileDAOFactory;
import com.avg.j2ee13.dao.filestore.helloworld.HelloWorldFileDAOImpl;

import java.util.HashMap;

public class HelloBOImpl implements HelloBO {

    private GenericDAOFactory daoFactory;

    public HelloBOImpl(GenericDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public String getMessage() {
        String message = "1";
        try {
            IGenericDAO dao = daoFactory.createDAO(HelloWorldFileDAOImpl.class, new HashMap());
            message = dao.findAll().toString();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return message;
    }

    public static void main(String[] args) {
        HelloBOImpl bo = new HelloBOImpl(new FileDAOFactory());
        bo.getMessage();
    }
}
