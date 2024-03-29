package com.avg.j2ee13.util.localization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Service Locator Implementation
 */
public class ServiceLocator {

    protected static final Log logger = LogFactory.getLog(ServiceLocator.class);

    private static final String ROOT_APPLICATION_NAME = "app";
    public static final String FILE_APP_CONFIGURATION = ROOT_APPLICATION_NAME + ".configuration.properties";
    private static final String P_EJB_FILE_CONFIGURATION = ROOT_APPLICATION_NAME + ".jndi.file";
    private static final String P_EJB_CTX_INITIAL_CONTEXT_FACTORY = ROOT_APPLICATION_NAME + "." + Context.INITIAL_CONTEXT_FACTORY;
    private static final String P_EJB_CTX_PROVIDER_URL = ROOT_APPLICATION_NAME + "." + Context.PROVIDER_URL;
    private static final String P_EJB_CTX_SECURITY_PRINCIPAL = ROOT_APPLICATION_NAME + "." + Context.SECURITY_PRINCIPAL;
    private static final String P_EJB_CTX_SECURITY_CREDENTIALS = ROOT_APPLICATION_NAME + "." + Context.SECURITY_CREDENTIALS;
    private static final String P_EJB_CTX_SECURITY_AUTHENTICATION = ROOT_APPLICATION_NAME + "." + Context.SECURITY_AUTHENTICATION;
    private static final String P_EJB_CTX_SECURITY_PROTOCOL = ROOT_APPLICATION_NAME + "." + Context.SECURITY_PROTOCOL;
    private static final String P_COMP_NAME_LOCAL = ROOT_APPLICATION_NAME + "java.naming.prefix.local";
    private static final String P_COMP_NAME_REMOTE = ROOT_APPLICATION_NAME + "java.naming.prefix.remote";
    private static final String P_APP_DATASOURCE = ROOT_APPLICATION_NAME + ".java.naming.datasource";
    private static final String P_DB_USER = ROOT_APPLICATION_NAME + ".bd.user";
    private static final String P_DB_PASSWORD = ROOT_APPLICATION_NAME + ".bd.password";
    private static final String P_DB_URL = ROOT_APPLICATION_NAME + ".bd.url";
    private static final String P_DB_DRIVER = ROOT_APPLICATION_NAME + ".bd.driver";
    private static final String P_DAO_FACTORY = ROOT_APPLICATION_NAME + ".dao.factory";
    private static final String P_DAO_FILE_STORE_LOCATION = ROOT_APPLICATION_NAME + ".dao.filestore.path";
    private static ServiceLocator instance;
    private HashMap services = new HashMap();
    private InitialContext initialContext;

    private ServiceLocator() throws LocalizationException {
        logger.debug("ServiceLocator.start");
        logger.info("ServiceLocator.start -> '-D" + FILE_APP_CONFIGURATION + "=" + System.getProperty(FILE_APP_CONFIGURATION) + "'");
        final PropertiesConfiguration appConfiguration = getAppConfiguration();

        if (appConfiguration == null) {
            throw new LocalizationException("ServiceLocator.start Set the location for  -> '-D" + FILE_APP_CONFIGURATION + "'");
        }

        final PropertiesConfiguration jndiConfiguration = getJndiConfiguration();
        if (jndiConfiguration.containsValue(P_EJB_CTX_INITIAL_CONTEXT_FACTORY, "default")) {
            logger.info("The EJB (jndi.properties) configuration is not set. The default Initial Context has been created. ");
            try {
                initialContext = new InitialContext();
            } catch (NamingException e) {
                throw new LocalizationException(e);
            }
        } else {

            if (jndiConfiguration.getProperty(P_EJB_CTX_INITIAL_CONTEXT_FACTORY) == null) {
                throw new LocalizationException("ServiceLocator: The property has not been set -> " + P_EJB_CTX_INITIAL_CONTEXT_FACTORY);
            }
            if (jndiConfiguration.getProperty(P_EJB_CTX_PROVIDER_URL) == null) {
                throw new LocalizationException("ServiceLocator: The property has not been set -> " + P_EJB_CTX_PROVIDER_URL);
            }

            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, jndiConfiguration.getProperty(P_EJB_CTX_INITIAL_CONTEXT_FACTORY));
            env.put(Context.PROVIDER_URL, jndiConfiguration.getProperty(P_EJB_CTX_PROVIDER_URL));

            String user = null;
            String password = null;
            String authentication = null;
            String protocol = null;
            try {
                user = jndiConfiguration.getProperty(P_EJB_CTX_SECURITY_PRINCIPAL);
                password = jndiConfiguration.getProperty(P_EJB_CTX_SECURITY_CREDENTIALS);
                authentication = jndiConfiguration.getProperty(P_EJB_CTX_SECURITY_AUTHENTICATION);
                protocol = appConfiguration.getProperty(P_EJB_CTX_SECURITY_PROTOCOL);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
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

            try {
                initialContext = new InitialContext(env);
            } catch (NamingException e) {
                throw new LocalizationException(e);
            }

        }
        logger.info("ServiceLocator.start Initial Context has been created.");
    }

    public static ServiceLocator getInstance() throws LocalizationException {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    private Object lookup(String jndiName) throws LocalizationException {
        Object object;
        try {
            if (services.containsKey(jndiName)) {
                object = services.get(jndiName);
            } else {
                logger.debug("looking for the resource JNDI -> " + jndiName);
                object = initialContext.lookup(jndiName);
                logger.debug("EJB has been found -> " + object);
                services.put(jndiName, object);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new LocalizationException("Error when trying to get the resource JNDI ->" + jndiName, e);
        }
        return object;
    }

    public EJBLocalHome getLocalHome(String jndiLocalHomeName) throws LocalizationException {
        EJBLocalHome home;
        String prefix = getJndiConfiguration().getProperty(P_COMP_NAME_LOCAL, "");
        String jndi = prefix + jndiLocalHomeName;
        try {
            home = (EJBLocalHome) lookup(jndi);
            logger.debug("EJBLocalHome has been found -> " + home);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new LocalizationException("Error when trying to get the EJB ->" + jndi, e);
        }
        return home;
    }

    public EJBHome getRemoteHome(String jndiHomeName, Class className) throws LocalizationException {
        EJBHome home;
        String prefix = getJndiConfiguration().getProperty(P_COMP_NAME_REMOTE, "");
        String jndi = prefix + jndiHomeName;
        try {
            Object objref = lookup(jndi);
            Object obj = PortableRemoteObject.narrow(objref, className);
            home = (EJBHome) obj;
            logger.debug("EJBHome (Remote) has been found -> " + home);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new LocalizationException("Error when trying to get the EJB ->" + jndi, e);
        }
        return home;
    }

    /**
     * @return
     * @throws LocalizationException
     * @See https://mvnrepository.com/artifact/javax.sql/jdbc-stdext
     * @since 1.4+
     */
    public DataSource getDataSource() throws LocalizationException {
        try {
            DataSource ds = (DataSource) services.get(P_APP_DATASOURCE);
            if (ds == null) {
                logger.debug("looking for the Datasource -> " + getJndiConfiguration().getProperty(P_APP_DATASOURCE));
                Object o = initialContext.lookup(getJndiConfiguration().getProperty(P_APP_DATASOURCE));
                ds = (DataSource) o;
                services.put(P_APP_DATASOURCE, ds);
            }
            return ds;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new LocalizationException("Error when trying to get the Datasource", e);
        }
    }

    public Connection getConnection() throws LocalizationException {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new LocalizationException(e);
        }
    }

    public Connection getConnection(String database) throws LocalizationException {
        Connection connection = (Connection) services.get(database);
        try {
            if (connection == null) {
                String userName = getJndiConfiguration().getProperty(P_DB_USER);
                String password = getJndiConfiguration().getProperty(P_DB_PASSWORD);
                String url = getJndiConfiguration().getProperty(P_DB_URL);
                String driver = getJndiConfiguration().getProperty(P_DB_DRIVER);

                Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(url, userName, password);
                services.put(database, connection);
            }
        } catch (SQLException e) {
            throw new LocalizationException(e);
        } catch (InstantiationException e) {
            throw new LocalizationException(e);
        } catch (IllegalAccessException e) {
            throw new LocalizationException(e);
        } catch (ClassNotFoundException e) {
            throw new LocalizationException(e);
        }
        return connection;

    }

    public PropertiesConfiguration getAppConfiguration() {
        return PropertiesConfiguration.getInstance(System.getProperty(FILE_APP_CONFIGURATION));
    }

    public PropertiesConfiguration getJndiConfiguration() {
        final String jndiPropertiesFile = getAppConfiguration().getProperty(P_EJB_FILE_CONFIGURATION);
        return PropertiesConfiguration.getInstance(jndiPropertiesFile);
    }

    public String getDAOFileDataSource() {
        return getAppConfiguration().getProperty(P_DAO_FILE_STORE_LOCATION);
    }

    public String getDAODBDataSource() {
        return getJndiConfiguration().getProperty(P_APP_DATASOURCE);
    }

    public int getDAOFactory() {
        return getAppConfiguration().getIntProperty(P_DAO_FACTORY);
    }
}
