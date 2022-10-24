package com.avg.j2ee13.dao.filestore.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.DAOParameters;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dao.IGenericDAO;
import com.avg.j2ee13.dto.HelloDTO;
import com.avg.j2ee13.util.localization.LocalizationException;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.MockObjectTestCase;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldFileDaoImplTest extends MockObjectTestCase {

    protected static final Log logger = LogFactory.getLog(HelloWorldFileDaoImplTest.class);
    ServiceLocator locator;

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.dao.filestore.helloworld.HelloWorldFileDaoImplTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());

    }

    public void test_insert(){
        try {
            locator = ServiceLocator.getInstance();
            HashMap parameters = new HashMap();
            parameters.put(DAOParameters.SERVICE_LOCATOR, locator);
            IGenericDAO daoFactory = GenericDAOFactory.getInstance().getDAO(DAOParameters.FILE_STORE, HelloWorldFileDAOImpl.class, parameters);

            HelloDTO dto = new HelloDTO();
            dto.setMessage("HelloWorldFileDaoImplTest");
            dto.setDateOfCreation(new Date());

            daoFactory.insert(dto);

        } catch (LocalizationException e) {
            logger.error(e);
            throw new RuntimeException();
        } catch (DAOException e) {
            logger.error(e);
            throw new RuntimeException();
        }

    }

    public static void main(String[] args) throws Exception {
        HelloWorldFileDaoImplTest test = new HelloWorldFileDaoImplTest();
        test.setUp();
        test.test_insert();
    }

}
