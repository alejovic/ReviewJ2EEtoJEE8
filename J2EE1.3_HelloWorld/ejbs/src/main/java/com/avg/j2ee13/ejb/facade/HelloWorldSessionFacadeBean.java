package com.avg.j2ee13.ejb.facade;

import com.avg.j2ee13.ejb.service.HelloWorldLocal;
import com.avg.j2ee13.ejb.service.HelloWorldLocalHome;
import com.avg.j2ee13.ejb.service.JNDINames;

import javax.ejb.CreateException;
import javax.naming.InitialContext;

public class HelloWorldSessionFacadeBean extends HelloWorldFacadeBean {

    private HelloWorldLocal service;

    public void initService() {
        log.info("HelloWorldSessionFacadeBean.initService started.");
        try {
            log.info("HelloWorldSessionFacadeBean.sessionContext -> " + getSessionContext());
            InitialContext context = new InitialContext();
            final HelloWorldLocalHome home = (HelloWorldLocalHome) context.lookup(JNDINames.HELLOWORLD_LOCAL_EJB);
            service = home.create();
            log.info("HelloWorldSessionFacadeBean.initService HelloWorldLocalHome created -> " + service);
        } catch (CreateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String sayHello(String name) {
        log.info("Hello from Session Facade [EJB Remote Service] -> " + name);
        return service.storeHello(name);
    }

    public String sayHello() {
        log.info("Hello from Session Facade [EJB Remote Service] -> default");
        return service.storeHello("HelloWorldSessionFacadeBean default");
    }

}
