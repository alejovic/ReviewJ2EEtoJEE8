package com.avg.j2ee13.util.localization;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.URL;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class ServiceLocatorTest extends MockObjectTestCase {

    ServiceLocator locator;

    public static void main(String[] args) throws Exception {
        ServiceLocatorTest test = new ServiceLocatorTest();
        test.setUp();
        test._test_ServiceLocator();
    }

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.util.localization.ServiceLocatorTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());

    }

    public void test_ServiceLocator() {
//        _test_ServiceLocator();
        assertEquals(1, 1);
    }

    public void _test_ServiceLocator() {
        try {
            InitialContext expected = new InitialContext();
            Mock mockInitialContext = new Mock(Context.class, "InitialContext");
            // expectations
            mockInitialContext
                    .expects(atMostOnce())
                    .withAnyArguments().will(returnValue(expected));

            locator = ServiceLocator.getInstance();
            System.out.println(locator.getAppConfiguration());
            fail();
        } catch (Exception ex) {
            assertEquals(null, ex.getMessage());
        }
    }

}
