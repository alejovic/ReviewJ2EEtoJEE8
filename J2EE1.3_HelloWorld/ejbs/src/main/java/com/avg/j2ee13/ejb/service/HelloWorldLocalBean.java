package com.avg.j2ee13.ejb.service;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.sql.DataSource;

/**
 * Bean Name="HelloWorldServiceBean"
 * description="An EJB (Application Service) named HelloWorld"
 * display-name="HelloWorldService"
 * jndi-name="HelloWorldService"
 * type="Stateless"
 * transaction-type="Container"
 */
public abstract class HelloWorldLocalBean implements SessionBean, HelloWorldLocal {

    protected static final Log log = LogFactory.getLog(HelloWorldLocalBean.class);

    private SessionContext sessionContext;
    private DataSource dataSource;

    public void ejbCreate() {
        log.info("HelloWorldLocalBean.ejbCreate stared.");
        try {
            final String dsName = ServiceLocator
                    .getInstance().getAppConfiguration().getProperty(ServiceLocator.P_APP_DATASOURCE);
            log.info("----------> dataSource dsName -> " + dsName);
            dataSource = (DataSource) getSessionContext().lookup(dsName);
        } catch (Exception e) {
            log.error("----------> dataSource not found", e);
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

    public SessionContext getSessionContext() {
        return sessionContext;
    }
}
