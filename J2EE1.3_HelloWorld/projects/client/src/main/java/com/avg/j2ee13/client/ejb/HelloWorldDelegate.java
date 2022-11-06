package com.avg.j2ee13.client.ejb;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import com.avg.j2ee13.ejb.facade.HelloWorldFacade;
import com.avg.j2ee13.ejb.facade.HelloWorldFacadeHome;
import com.avg.j2ee13.ejb.service.HelloWorldLocalHome;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.CreateException;
import java.rmi.RemoteException;

public class HelloWorldDelegate extends GenericDelegate implements IHelloWorldBusiness {

    protected static final Log log = LogFactory.getLog(HelloWorldDelegate.class);
    private static final String EJB_NAME = "helloWorldBean";
    private static final Class HOME_CLASS = HelloWorldFacadeHome.class;

    private Object objectRef;

    public HelloWorldDelegate() throws Exception {
        super();
    }

    public void init() throws RemoteException, CreateException {
        if (isRemote()) {
            HelloWorldFacadeHome remoteHome = (HelloWorldFacadeHome) getEjbHome();
            objectRef = remoteHome.create();
        } else {
            HelloWorldLocalHome localHome = (HelloWorldLocalHome) getEjbLocalHome();
            objectRef = localHome.create();
        }
    }

    public Class getHomeClass() {
        return HOME_CLASS;
    }

    public String getEJBName() {
        return EJB_NAME;
    }

    public String sayHello(final String name) {
        log.debug("HelloWorldDelegate.sayHello");
        try {
            HelloWorldFacade remoteObject = (HelloWorldFacade) objectRef;
            return remoteObject.sayHello(name);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String sayHello() {
        try {
            HelloWorldFacade remoteObject = (HelloWorldFacade) objectRef;
            return remoteObject.sayHello();
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
