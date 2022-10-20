package com.avg.j2ee13.ejb.helloworld;

import javax.ejb.SessionBean;

public class HelloWorldSessionBean extends HelloWorldBean implements SessionBean {

    public String sayHello(String name) {
        return "Hello," + name + " ->connect BOs!!";
    }

    public String sayHello() {
        return sayHello("default");
    }
}
