package com.avg.j2ee13.client.ejb;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import com.avg.j2ee13.ejb.helloworld.HelloWorldRemoteHome;
import com.avg.j2ee13.ejb.helloworld.HelloWorldRemoteObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.CreateException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class HelloWorldDelegate extends GenericDelegate implements IHelloWorldBusiness {

    protected static final Log log = LogFactory.getLog(HelloWorldDelegate.class);
    private static final String EJB_NAME = "helloWorldBean";

    private HelloWorldRemoteObject service;

    public HelloWorldDelegate() throws Exception {
        super();
    }

    public void init() throws RemoteException, CreateException {
        if (isRemote()) {
            HelloWorldRemoteHome remoteHome = (HelloWorldRemoteHome) PortableRemoteObject.narrow(getEjbHome(), HelloWorldRemoteHome.class);
            service = remoteHome.create();
        } else {
            HelloWorldRemoteHome remoteHome = (HelloWorldRemoteHome) getEjbHome();
            service = remoteHome.create();
        }
    }

    public String getEJBName() {
        return EJB_NAME;
    }

    public String sayHello(final String name) {
        log.debug("HelloWorldDelegate.sayHello");
        try {
            return service.sayHello(name);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String sayHello() {
        try {
            return service.sayHello();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
