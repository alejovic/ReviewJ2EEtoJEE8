package com.avg.j2ee13.bo.strategy;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.util.localization.PropertiesConfiguration;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.jmock.MockObjectTestCase;

import java.net.URL;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldTransferTest extends MockObjectTestCase {

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.bo.strategy.HelloWorldTransferTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());
        PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.FILE_APP_CONFIGURATION));
    }

    public void test_transfer() {
        HelloWorldTransferBO transferBO = new HelloWorldTransferBO();
        try {
            transferBO.transferData(new HelloWorldTransferFromMemoryToFileBO());
        } catch (BOException e) {
            fail(e.getMessage());
        }
        assertEquals(1, 1);

    }

}
