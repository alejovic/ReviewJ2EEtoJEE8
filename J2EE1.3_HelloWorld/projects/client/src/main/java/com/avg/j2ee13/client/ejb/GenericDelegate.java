package com.avg.j2ee13.client.ejb;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import java.rmi.RemoteException;

/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients and business
 * services. The Business Delegate hides the underlying implementation details of the business service, such as lookup
 * and access details of the EJB architecture
 */
public abstract class GenericDelegate {

    protected static final Log log = LogFactory.getLog(GenericDelegate.class);

    private static final String EJB_SERVICE = ".lookup";
    private static final String JNDI_EJB_LOCAL = ".local";
    private static final String JNDI_EJB_REMOTE = ".remote";

    private EJBHome ejbHome;
    private EJBLocalHome ejbLocalHome;
    private ServiceLocator locator;

    public GenericDelegate() throws Exception {
        log.debug("GenericDelegate.constructor");
        locator = ServiceLocator.getInstance();
        String jndi;

        if (isRemote()) {
            jndi = locator.getJndiConfiguration().getProperty(getJndiEJBRemoteName());
            ejbHome = locator.getRemoteHome(jndi, getHomeClass());
        } else {
            jndi = locator.getJndiConfiguration().getProperty(getJndiEJBLocalName());
            ejbLocalHome = locator.getLocalHome(jndi);
        }
        init();
    }

    public abstract void init() throws RemoteException, CreateException;

    public boolean isRemote() {
        log.debug("GenericDelegate.isRemote");
        return "remote".equals(locator.getJndiConfiguration().getProperty(getEJBService()));
    }

    public abstract Class getHomeClass();
    public abstract String getEJBName();

    public String getEJBService() {
        return getEJBName() + EJB_SERVICE;
    }

    public String getJndiEJBRemoteName() {
        return getEJBName() + JNDI_EJB_REMOTE;
    }

    public String getJndiEJBLocalName() {
        return getEJBName() + JNDI_EJB_LOCAL;
    }

    public EJBHome getEjbHome() {
        return ejbHome;
    }

    public EJBLocalHome getEjbLocalHome() {
        return ejbLocalHome;
    }
}
