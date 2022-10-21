package com.avg.j2ee13.ejb.service;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.sql.DataSource;
import java.sql.Connection;

public abstract class GenericServiceLocalBean implements SessionBean {

    protected static final Log log = LogFactory.getLog(GenericServiceLocalBean.class);

    protected SessionContext sessionContext;
    protected DataSource dataSource;
    protected ServiceLocator locator;

    public void ejbCreate() {
        log.info("GenericServiceLocalBean.ejbCreate stared.");
        try {
            locator = ServiceLocator.getInstance();
            dataSource = locator.getDataSource();
            log.info("GenericServiceLocalBean.ejbCreate DataSource created -> " + dataSource);
        } catch (Exception e) {
            log.error("GenericServiceLocalBean.ejbCreate ----------> dataSource not found", e);
        }
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

}
