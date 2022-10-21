package com.avg.j2ee13.ejb.facade;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import com.avg.j2ee13.util.localization.PropertiesConfiguration;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public abstract class GenericFacadeBean implements SessionBean {

    protected static final Log log = LogFactory.getLog(GenericFacadeBean.class);

    private SessionContext sessionContext;

    public void ejbCreate() {
        log.info("GenericFacadeBean.ejbCreate ->" +this.getClass());
        String path = "/mnt/data/workspace/DEV/ReviewJ2EEtoJEE8/J2EE1.3_HelloWorld/ear/src/main/resources/";

        if (System.getProperty(ServiceLocator.FILE_APP_CONFIGURATION) == null) {
            String appFileName = path + "app.configuration.properties";
            System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, appFileName);
            PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.FILE_APP_CONFIGURATION));
        }
        initService();
    }

    public abstract void initService();

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
