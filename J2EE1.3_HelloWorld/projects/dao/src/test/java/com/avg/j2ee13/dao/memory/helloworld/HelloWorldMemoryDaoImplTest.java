package com.avg.j2ee13.dao.memory.helloworld;

import com.avg.j2ee13.dao.DAOClassList;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.IGenericDAO;
import com.avg.j2ee13.dao.memory.MemoryDAOFactory;
import com.avg.j2ee13.dto.HelloDTO;
import com.avg.j2ee13.util.localization.ServiceLocator;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.MockObjectTestCase;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class HelloWorldMemoryDaoImplTest extends MockObjectTestCase {

    protected static final Log logger = LogFactory.getLog(HelloWorldMemoryDaoImplTest.class);
    ServiceLocator locator;
    IGenericDAO daoFactory;

    public static void main(String[] args) throws Exception {
        HelloWorldMemoryDaoImplTest test = new HelloWorldMemoryDaoImplTest();
        test.setUp();
        test.test_insert();
    }

    public void test_dummy() {
        Assert.assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.dao.memory.helloworld.HelloWorldMemoryDaoImplTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());

        locator = ServiceLocator.getInstance();

        daoFactory = new MemoryDAOFactory().createDAO(DAOClassList.HelloWorld);

    }

    public void test_insert() {
        try {
            final HelloDTO dto = new HelloDTO();
            dto.setMessage("HelloWorldMemoryDaoImplTest");
            dto.setDateOfCreation(new Date());
            daoFactory.insert(dto);
            Assert.assertEquals(1, 1);

            List dtos = daoFactory.findAll();
            for (int index = 0; index < dtos.size(); index++) {
                logger.info(dtos.get(index).toString());
            }

        } catch (DAOException e) {
            logger.error(e);
            throw new RuntimeException();
        }

    }

    public void test_delete() {
        try {
            HelloDTO dto = new HelloDTO();
            dto.setId(1L);
            daoFactory.delete(dto);
            Assert.assertEquals(1, 1);
        } catch (DAOException e) {
            logger.error(e);
            throw new RuntimeException();
        }

    }

}
