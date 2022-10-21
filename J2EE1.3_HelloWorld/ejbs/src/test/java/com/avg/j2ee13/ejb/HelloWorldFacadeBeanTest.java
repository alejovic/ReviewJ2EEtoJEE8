package com.avg.j2ee13.ejb;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import com.avg.j2ee13.ejb.facade.HelloWorldFacadeHome;
import com.avg.j2ee13.ejb.service.HelloWorldLocalHome;
import com.avg.j2ee13.ejb.service.HelloWorldLocal;
import com.avg.j2ee13.ejb.facade.HelloWorldFacade;
import com.avg.j2ee13.util.localization.PropertiesConfiguration;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.jmock.MockObjectTestCase;

import javax.ejb.CreateException;
import javax.ejb.EJBMetaData;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldFacadeBeanTest extends MockObjectTestCase  {

    public static final String JNDI_NAME_REMOTE = "HelloWorldFacadeBean";
    public static final String JNDI_NAME_LOCAL = "ejbs-1.0_HelloWorldLocalBeanLocal";

    private InitialContext context;
    private HelloWorldFacade remoteObject;

    public void test_dummy(){
        assertEquals(1,1);
    }

    public void setUp() throws Exception {
        initProperties();
        initContext(true);
    }

    public void initProperties() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.ejb.HelloWorldFacadeBeanTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());
        PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.FILE_APP_CONFIGURATION));

    }

    private void initContext(boolean useJndiProperties) throws NamingException {
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

    public HelloWorldFacade getRemote() throws NamingException, RemoteException, CreateException {
        Object remoteEjb = context.lookup(JNDI_NAME_REMOTE);
        System.out.println(remoteEjb.toString());

        HelloWorldFacadeHome remoteHome = (HelloWorldFacadeHome) PortableRemoteObject.narrow(remoteEjb, HelloWorldFacadeHome.class);
        remoteObject = remoteHome.create();
        EJBMetaData metaData = remoteHome.getEJBMetaData();
        System.out.println("Is session: " + metaData.isSession());
        System.out.println("Is stateless session: " + metaData.isStatelessSession());
        System.out.println("Home interface class: " + metaData.getHomeInterfaceClass());
        return remoteObject;
    }

    public HelloWorldLocal getLocal() throws CreateException, NamingException {
        Object localEjb = context.lookup(JNDI_NAME_LOCAL);
        System.out.println(localEjb.toString());

        HelloWorldLocalHome localHome = (HelloWorldLocalHome) localEjb;
        HelloWorldLocal localObject = localHome.create();
        return localObject;
    }

    public void _testEJBRemote() {
        try {
            HelloWorldFacade remoteObject = getRemote();
            String str =  remoteObject.sayHello("HelloWorldFacadeBeanTest Remote");
            System.out.println(str);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public void _testEJBLocal() {
        try {
            HelloWorldLocal localObject = getLocal();
            String str = localObject.storeHello("HelloWorldFacadeBeanTest Local");
            System.out.println(str);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public void test_sayHello(){
        assertEquals(1, 1);
    }

    public static void main(String[] args) {
        try {
            HelloWorldFacadeBeanTest test = new HelloWorldFacadeBeanTest();
            test.initContext(true);
            test._testEJBRemote();
            //test._testEJBLocal();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
