package com.avg.j2ee13.web.listeners;

import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

public class AppContextListener implements ServletContextListener {

    private Log logger = LogFactory.getLog(AppContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Context initialized for Application.");

        //replace this by conf
        // returns the Class object associated with this class
        Class clazz = null;
        try {
            clazz = Class.forName("com.avg.j2ee13.web.listeners.AppContextListener");
            // returns the ClassLoader object associated with this Class.
            ClassLoader classLoader = clazz.getClassLoader();
            URL configurationUrl = classLoader.getResource("struts.configuration.properties");
            System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }


        ServletContext ctx = servletContextEvent.getServletContext();
        String ctxName = ctx.getServletContextName();
        logger.info("Context Name -> " + ctxName);

        String parameter = ctx.getInitParameter("com.avg.parameters.parameter");
        ctx.setAttribute("custom_parameter", parameter);
        logger.info("Custom Parameter from context -> " + parameter);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Context closed for Application.");

        ServletContext ctx = servletContextEvent.getServletContext();
        String parameter = (String) ctx.getAttribute("custom_parameter");
        logger.info("Custom Parameter from context -> " + parameter);
    }

}
