package com.avg.j2ee13.ejb;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import com.avg.j2ee13.ejb.helloworld.HelloWorldLocalHome;
import com.avg.j2ee13.ejb.helloworld.HelloWorldLocalObject;
import com.avg.j2ee13.ejb.helloworld.HelloWorldRemoteHome;
import com.avg.j2ee13.ejb.helloworld.HelloWorldRemoteObject;
import org.jmock.MockObjectTestCase;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldBeanTest extends MockObjectTestCase implements IHelloWorldBusiness {

    public static final String JNDI_NAME = "HelloWorldBean";

    private InitialContext context;
    private HelloWorldRemoteObject remoteObject;

    public void test_dummy(){
        assertEquals(1,1);
    }

    public void setUp() throws Exception {
        init(true);
    }

    private void init(boolean useJndiProperties) throws NamingException {
        if (useJndiProperties) {
            // jndi.properties
            context = new InitialContext();
        } else {
            Hashtable env = new Hashtable();
            env.put(Context.PROVIDER_URL, "ormi://127.0.0.1:23791/HelloJ2EE13");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.evermind.server.rmi.RMIInitialContextFactory");
            env.put(Context.SECURITY_PRINCIPAL, "oc4jadmin");
            env.put(Context.SECURITY_CREDENTIALS, "oc4jadmin");
            context = new InitialContext(env);
        }
    }

    public HelloWorldRemoteObject getRemote() throws NamingException, RemoteException, CreateException {
        Object remoteEjb = context.lookup(JNDI_NAME);
        System.out.println(remoteEjb.toString());

        HelloWorldRemoteHome remoteHome = (HelloWorldRemoteHome) PortableRemoteObject.narrow(remoteEjb, HelloWorldRemoteHome.class);
        remoteObject = remoteHome.create();
        return remoteObject;
    }

    public HelloWorldLocalObject getLocal() throws CreateException, NamingException {
        Object localEjb = context.lookup(JNDI_NAME);
        System.out.println(localEjb.toString());

        HelloWorldLocalHome localHome = (HelloWorldLocalHome) localEjb;
        HelloWorldLocalObject localObject = localHome.create();
        return localObject;
    }

    public String sayHello(String myName) {
        try {
            HelloWorldRemoteObject remoteObject = getRemote();
            return remoteObject.sayHello(myName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public String sayHello() {
        try {
            HelloWorldRemoteObject remoteObject = getRemote();
            return remoteObject.sayHello();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public void _test_sayHello(){
        assertEquals(sayHello("HelloWorldBeanTest JUnit!!!"),"Hello,HelloWorldBeanTest JUnit!!! ->connect BOs!!");
    }

    public static void main(String[] args) {
        try {
            HelloWorldBeanTest test = new HelloWorldBeanTest();
            test.init(true);
            System.out.println(test.sayHello("HelloWorldBeanTest JUnit!!!"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
