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
            URL configurationUrl = classLoader.getResource("app.configuration.properties");
            System.setProperty(ServiceLocator.FILE_APP_CONFIGURATION, configurationUrl.getFile());

            HelloWorldDelegate delegate = new HelloWorldDelegate();
            System.out.println(delegate.sayHello("terminal-standalone"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
