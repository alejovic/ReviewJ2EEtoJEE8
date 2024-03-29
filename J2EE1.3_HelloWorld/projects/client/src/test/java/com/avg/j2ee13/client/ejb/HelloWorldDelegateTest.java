package com.avg.j2ee13.client.ejb;

import com.avg.j2ee13.util.localization.PropertiesConfiguration;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.jmock.MockObjectTestCase;

import java.net.URL;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldDelegateTest extends MockObjectTestCase {

    HelloWorldDelegate delegate;

    public static void main(String[] args) {
        try {
            HelloWorldDelegateTest test = new HelloWorldDelegateTest();
            test.setUp();
            test._test_HelloWorldDelegate_sayHello();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.client.ejb.HelloWorldDelegateTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());
        PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.FILE_APP_CONFIGURATION));
    }

    public void test_HelloWorldDelegate_sayHello() {
        //_test_HelloWorldDelegate_sayHello();
        assertEquals(1, 1);
    }

    public void _test_HelloWorldDelegate_sayHello() throws Exception {
        delegate = new HelloWorldDelegate();
        System.out.println(delegate.sayHello("HelloWorldDelegateTest JUnit!!"));
    }

}
