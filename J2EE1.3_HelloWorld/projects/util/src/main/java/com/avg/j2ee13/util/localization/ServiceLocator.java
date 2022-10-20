package com.avg.j2ee13.util.localization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Properties;

/**
 * Service Locator Implementation
 */
public class ServiceLocator {

    protected static final Log log = LogFactory.getLog(ServiceLocator.class);

    private static final String ROOT_APPLICATION_PROPERTY = "app";

    public static final String P_APP_CONFIGURATION = ROOT_APPLICATION_PROPERTY + ".configuration.properties";
    public static final String P_EJB_CONFIGURATION = ROOT_APPLICATION_PROPERTY + ".jndi.properties";

    private static final String P_INITIAL_CONTEXT_FACTORY = ROOT_APPLICATION_PROPERTY + "." + Context.INITIAL_CONTEXT_FACTORY;
    private static final String P_PROVIDER_URL = ROOT_APPLICATION_PROPERTY + "." + Context.PROVIDER_URL;
    private static final String P_SECURITY_PRINCIPAL = ROOT_APPLICATION_PROPERTY + "." + Context.SECURITY_PRINCIPAL;
    private static final String P_SECURITY_CREDENTIALS = ROOT_APPLICATION_PROPERTY + "." + Context.SECURITY_CREDENTIALS;
    private static final String P_SECURITY_AUTHENTICATION = ROOT_APPLICATION_PROPERTY + "." + Context.SECURITY_AUTHENTICATION;
    private static final String P_SECURITY_PROTOCOL = ROOT_APPLICATION_PROPERTY + "." + Context.SECURITY_PROTOCOL;

    private static final String P_COMP_NAME = ROOT_APPLICATION_PROPERTY + "java.naming.prefix";

    private static final String P_DB_DATASOURCE = ROOT_APPLICATION_PROPERTY + ".bd.datasource";
    private static final String P_DB_USER = ROOT_APPLICATION_PROPERTY + ".bd.user";
    private static final String P_DB_PASSWORD = ROOT_APPLICATION_PROPERTY + ".bd.password";
    private static final String P_DB_URL = ROOT_APPLICATION_PROPERTY + ".bd.url";
    private static final String P_DB_DRIVER = ROOT_APPLICATION_PROPERTY + ".bd.driver";

    private static final String jndiDataSourceName = "java:";

    private InitialContext initialContext;

    private static ServiceLocator instance;

    private HashMap services;

    private PropertiesConfiguration configuration;

    private ServiceLocator() throws NamingException {
        log.debug("ServiceLocator.start");
        services = new HashMap();
        configuration = PropertiesConfiguration.getInstance(System.getProperty(P_EJB_CONFIGURATION));

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, configuration.getProperty(P_INITIAL_CONTEXT_FACTORY));
        env.put(Context.PROVIDER_URL, configuration.getProperty(P_PROVIDER_URL));

        String user = null;
        String password = null;
        String authentication = null;
        String protocol = null;
        try {
            user = configuration.getProperty(P_SECURITY_PRINCIPAL);
            password = configuration.getProperty(P_SECURITY_CREDENTIALS);
            authentication = configuration.getProperty(P_SECURITY_AUTHENTICATION);
            protocol = configuration.getProperty(P_SECURITY_PROTOCOL);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        if (user != null && user.length() > 0 && password != null && password.length() > 0) {
            env.setProperty(Context.SECURITY_PRINCIPAL, user);
            env.setProperty(Context.SECURITY_CREDENTIALS, password);
            if (authentication != null && authentication.length() > 0) {
                env.put(Context.SECURITY_AUTHENTICATION, authentication);
            }
            if (protocol != null && protocol.length() > 0) {
                env.put(Context.SECURITY_PROTOCOL, protocol);
            }

        }

        initialContext = new InitialContext(env);
        log.info("Initial Context has been created -> " + configuration.getProperty(P_PROVIDER_URL));
    }

    public static ServiceLocator getInstance() throws NamingException {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public Object getEJB(String beanName) throws Exception {
        Object object;
        String prefix = configuration.getProperty(P_COMP_NAME, "");
        String jndi = prefix + beanName;
        try {
            if (services.containsKey(jndi)) {
                object = services.get(jndi);
            } else {
                log.debug("looking for the EJB -> " + jndi);
                object = initialContext.lookup(jndi);
                log.debug("EJB has been found -> " + object);
                services.put(jndi, object);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new Exception("Error when trying to get the EJB ->" + jndi, e);
        }
        return object;
    }

    public DataSource getDataSource() throws Exception {
        try {
            DataSource ds = (DataSource) services.get(P_DB_DATASOURCE);
            if (ds == null) {
                log.debug("looking for the Datasource -> " + jndiDataSourceName + configuration.getProperty(P_DB_DATASOURCE));
                Object o = initialContext.lookup(jndiDataSourceName + configuration.getProperty(P_DB_DATASOURCE));
                ds = (DataSource) o;
                services.put(P_DB_DATASOURCE, ds);
            }
            return ds;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new Exception("Error when trying to get the Datasource", e);
        }
    }

    public Connection getConnection() throws Exception {
        return getDataSource().getConnection();
    }

    public Connection getConnection(String database) throws Exception {
        Connection connection = (Connection) services.get(database);
        if (connection == null || (connection != null && connection.isClosed())) {
            String userName = configuration.getProperty(P_DB_USER);
            String password = configuration.getProperty(P_DB_PASSWORD);
            String url = configuration.getProperty(P_DB_URL);
            String driver = configuration.getProperty(P_DB_DRIVER);

            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            services.put(database, connection);
        }
        return connection;

    }

    public PropertiesConfiguration getConfiguration() {
        return configuration;
    }
}
