package com.avg.j2ee13.ejb.service;

/**
 * Implementing the Local Component Interface
 * The local component interface defines the business methods of the bean that a local (collocated) client can invoke.
 * The requirements for developing the local component interface include the following:
 * <p>
 * The local component interface of the bean must extend the javax.ejb.EJBLocalObject interface.
 * <p>
 * You declare the local component interface and its methods as public.
 */

/**
 * Local Component Interface for HelloWorld which define internal Business Methods
 */
public interface HelloWorldLocal extends javax.ejb.EJBLocalObject {
    public String storeHello(String myName);

}
