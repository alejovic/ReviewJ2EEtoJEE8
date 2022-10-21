package com.avg.j2ee13.web.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private Log logger = LogFactory.getLog(AppContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Context initialized for Application.");

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
