package com.avg.j2ee13.terminal;

import com.avg.j2ee13.client.ejb.HelloWorldDelegate;
import com.avg.j2ee13.util.localization.ServiceLocator;

import java.net.URL;

public class TerminalClient {

    public static void main(String[] args) {
        try {
            // returns the Class object associated with this class
            Class clazz = Class.forName("com.avg.j2ee13.terminal.TerminalClient");
            // returns the ClassLoader object associated with this Class.
            ClassLoader classLoader = clazz.getClassLoader();
            URL url = classLoader.getResource("app.jndi.properties");
            System.setProperty(ServiceLocator.P_EJB_CONFIGURATION, url.getFile());

            HelloWorldDelegate delegate = new HelloWorldDelegate();
            System.out.println(delegate.sayHello("terminal-standalone"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
