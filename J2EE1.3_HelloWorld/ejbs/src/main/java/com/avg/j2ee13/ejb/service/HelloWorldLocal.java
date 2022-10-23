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

import com.avg.j2ee13.dto.HelloDTO;

/**
 * Local Component Interface for HelloWorld which defines internal Business Methods
 */
public interface HelloWorldLocal extends javax.ejb.EJBLocalObject {
    public HelloDTO storeMessage(String message);

    public HelloDTO getMessage(long id);

}
