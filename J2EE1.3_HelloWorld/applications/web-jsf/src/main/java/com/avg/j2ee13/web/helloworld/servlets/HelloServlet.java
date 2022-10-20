package com.avg.j2ee13.web.helloworld.servlets;

import com.avg.j2ee13.web.helloworld.listeners.AppContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    private Log logger = LogFactory.getLog(AppContextListener.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        logger.debug("HelloServlet.doGet -> Started.");

        ServletContext ctx = req.getSession().getServletContext();
        String parameter = (String) ctx.getAttribute("custom_parameter");
        logger.info("Custom Parameter from context -> " + parameter);

        PrintWriter out = res.getWriter();
        out.println("Hello, world! " + parameter);
        out.close();
    }
}
