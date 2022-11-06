package com.avg.j2ee13.util.localization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Junit 3.8.2 http://dev.cs.ovgu.de/java/junit/javadoc/junit/framework/TestCase.html
 * jMock 1.2.0 @see http://jmock.org/jmock1-getting-started.html
 */
public class DataSourceTest extends MockObjectTestCase {

    protected static final Log logger = LogFactory.getLog(ServiceLocator.class);
    private static final String QUERY_POSTGRESQL = "SELECT 1";
    private static final String QUERY_ORACLE = "SELECT * FROM DUAL";

    ServiceLocator locator;

    public static void main(String[] args) throws Exception {
        DataSourceTest test = new DataSourceTest();
        test.setUp();
        test._test_DataSource();
        test._test_Connection();
    }

    public void test_dummy() {
        assertEquals(1, 1);
    }

    public void setUp() throws Exception {
        Class clazz = Class.forName("com.avg.j2ee13.util.localization.DataSourceTest");
        // returns the ClassLoader object associated with this Class.
        ClassLoader classLoader = clazz.getClassLoader();

        URL configurationUrl = classLoader.getResource("test.configuration.properties");
        System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());

    }

    public void test_DataSource() {
        _test_DataSource();
        assertEquals(1, 1);
    }

    public void _test_Connection() {
        try {
            InitialContext expected = new InitialContext();
            Mock mockInitialContext = new Mock(Context.class, "InitialContext");
            // expectations
            mockInitialContext
                    .expects(atMostOnce())
                    .withAnyArguments().will(returnValue(expected));

            locator = ServiceLocator.getInstance();

            String userName = locator.getJndiConfiguration().getProperty("app.bd.user", "Not Defined.");
            String password = locator.getJndiConfiguration().getProperty("app.bd.password", "Not Defined.");
            String url = locator.getJndiConfiguration().getProperty("app.bd.url", "Not Defined.");
            String driver = locator.getJndiConfiguration().getProperty("app.bd.driver", "Not Defined.");
            logger.info("username -> " + userName);
            logger.info("password -> " + password);
            logger.info("url -> " + url);
            logger.info("driver -> " + driver);

            Connection connection = locator.getConnection("HELLOWORLD");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_ORACLE);
            // Extract data from result set
            while (rs.next()) {
                logger.info("Resultset -> " + rs.getString(1));
            }

            String datasource = locator.getJndiConfiguration().getProperty("app.bd.datasource", "Not Defined.");
            logger.info("datasource -> " + datasource);

        } catch (Exception ex) {
            ex.printStackTrace();
            assertEquals(null, ex.getMessage());
        }
    }

    public void _test_DataSource() {
        try {
            InitialContext expected = new InitialContext();
            Mock mockInitialContext = new Mock(Context.class, "InitialContext");
            // expectations
            mockInitialContext
                    .expects(atMostOnce())
                    .withAnyArguments().will(returnValue(expected));

            locator = ServiceLocator.getInstance();

            String datasource = locator.getJndiConfiguration().getProperty("app.bd.datasource", "Not Defined.");
            logger.info("datasource -> " + datasource);

            DataSource dataSource = locator.getDataSource();
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_ORACLE);
            // Extract data from result set
            while (rs.next()) {
                logger.info("Resultset -> " + rs.getString(1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            assertEquals(null, ex.getMessage());
        }
    }

}
