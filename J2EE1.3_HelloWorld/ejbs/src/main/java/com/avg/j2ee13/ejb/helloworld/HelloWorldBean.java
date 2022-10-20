package com.avg.j2ee13.ejb.helloworld;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Bean Name="HelloWorld"
 * description="An EJB named HelloWorld"
 * display-name="HelloWorld"
 * jndi-name="HelloWorld"
 * type="Stateless"
 * transaction-type="Container"
 */
public abstract class HelloWorldBean implements SessionBean, IHelloWorldBusiness {

    protected static final Log log = LogFactory.getLog(HelloWorldBean.class);

    protected SessionContext sessionContext;

    public void ejbCreate() {
        log.info("HelloWorldBean.ejbCreate");
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
