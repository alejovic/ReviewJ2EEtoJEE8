package com.avg.j2ee13.util.localization;

import org.jmock.MockObjectTestCase;

import java.net.URL;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html https://jmockit.github.io/tutorial/Mocking.html
 */
public class PropertiesConfigurationTest extends MockObjectTestCase {

    PropertiesConfiguration jndiConfiguration;
    PropertiesConfiguration appConfiguration;

    public void test_dummy(){
        assertEquals(1,1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.util.localization.PropertiesConfigurationTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.P_APP_CONFIGURATION, configurationUrl.getFile());
        appConfiguration = PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.P_APP_CONFIGURATION));

        URL jndiUrl = classLoader.getResource("test.jndi.properties");
        System.setProperty(ServiceLocator.P_EJB_CONFIGURATION, jndiUrl.getFile());
        jndiConfiguration = PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.P_EJB_CONFIGURATION));
    }

    public void test_Configuration() {
        _test_Configuration();
        assertEquals(1, 1);
    }

    public void _test_Configuration() {
        System.out.println("appConfiguration::app.property1 -> " + appConfiguration.getProperty("app.property1"));
        assertEquals(appConfiguration.getProperty("app.property1"), "This is a property from the file");
        System.out.println("jndiConfiguration:: app.java.naming.factory.initial - " + jndiConfiguration.getProperty("app.java.naming.factory.initial"));
        assertEquals(jndiConfiguration.getProperty("app.java.naming.factory.initial"), "com.evermind.server.ApplicationClientInitialContextFactory");
    }

    public static void main(String[] args) throws Exception {
        PropertiesConfigurationTest test = new PropertiesConfigurationTest();
        test.setUp();
        test._test_Configuration();
    }

}
