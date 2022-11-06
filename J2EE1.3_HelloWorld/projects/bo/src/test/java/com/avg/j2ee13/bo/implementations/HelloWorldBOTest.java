package com.avg.j2ee13.bo.implementations;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.dao.DAOFactoryMaker;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.util.localization.PropertiesConfiguration;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.jmock.MockObjectTestCase;

import java.net.URL;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldBOTest extends MockObjectTestCase {

    private GenericDAOFactory daoFactory;

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.bo.implementations.HelloWorldBOTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());
        PropertiesConfiguration.getInstance(System.getProperty(ServiceLocator.FILE_APP_CONFIGURATION));

        daoFactory = DAOFactoryMaker.getInstance().createDAOFactory(DAOFactoryMaker.FACTORY_FILE_STORE);
    }

    public void test_transfer() {
        HelloWorldBO bo = new HelloWorldBO(daoFactory);
        try {
            bo.storeMessage("HelloWorldBOTest");
        } catch (BOException e) {
            fail(e.getMessage());
        }
        assertEquals(1, 1);

    }

}
