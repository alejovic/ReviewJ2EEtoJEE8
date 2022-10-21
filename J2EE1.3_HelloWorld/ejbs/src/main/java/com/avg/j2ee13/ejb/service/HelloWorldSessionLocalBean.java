package com.avg.j2ee13.ejb.service;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;

public class HelloWorldSessionLocalBean extends HelloWorldLocalBean {

    public String storeHello(String myName) {
        log.info("HelloWorldSessionLocalBean.storeHello started");
        return "Hello from Application Service [EJB Local Service] -> " + myName;
    }

    public EJBLocalHome getEJBLocalHome() throws EJBException {
        return null;
    }

    public Object getPrimaryKey() throws EJBException {
        return null;
    }

    public void remove() throws RemoveException, EJBException {

    }

    public boolean isIdentical(EJBLocalObject ejbLocalObject) throws EJBException {
        return false;
    }
}
