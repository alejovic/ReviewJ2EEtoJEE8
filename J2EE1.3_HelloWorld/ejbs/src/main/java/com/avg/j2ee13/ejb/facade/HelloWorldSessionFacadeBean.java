package com.avg.j2ee13.ejb.facade;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.dto.HelloDTO;
import com.avg.j2ee13.ejb.service.HelloWorldLocal;
import com.avg.j2ee13.ejb.service.HelloWorldLocalHome;
import com.avg.j2ee13.ejb.service.JNDILocalNames;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import java.rmi.RemoteException;

/**
 * @see ejb-jar.xml
 * Bean Name="HelloWorldFacadeBean"
 * description="An Session Facade EJB named HelloWorldFacadeBean"
 * display-name="HelloWorldFacadeBean"
 * jndi-name="HelloWorldFacadeBean"
 * type="Stateless"
 * transaction-type="Container"
 */
public class HelloWorldSessionFacadeBean extends GenericSessionFacadeBean {

    private HelloWorldLocal service;

    public void initService() {
        log.info("HelloWorldSessionFacadeBean.initService started.");
        try {
            log.info("HelloWorldSessionFacadeBean.sessionContext -> " + getSessionContext());

            String mailenv = (String) getSessionContext().lookup("env.entry.mailhost");
            log.info("HelloWorldSessionFacadeBean. env-entry::mailenv -> " + mailenv);

            InitialContext context = new InitialContext();
            final HelloWorldLocalHome home = (HelloWorldLocalHome) context.lookup(JNDILocalNames.EJB_HELLO_WORLD);
            service = home.create();
            log.info("HelloWorldSessionFacadeBean.initService HelloWorldLocalHome created -> " + service);
        } catch (CreateException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public String sayHello(String name) throws RemoteException {
        log.info("Hello from Session Facade [EJB Remote Service] -> " + name);
        HelloDTO dto;
        try {
            dto = service.storeMessage(name);
        } catch (BOException e) {
            throw new RemoteException(e.getMessage(), e);
        }
        return dto.getId() + "";
    }

    public String sayHello() throws RemoteException {
        log.info("Hello from Session Facade [EJB Remote Service] -> default");
        HelloDTO dto;
        try {
            dto = service.storeMessage("default");
        } catch (BOException e) {
            throw new RemoteException(e.getMessage(), e);
        }
        return dto.getId() + "";
    }

}
