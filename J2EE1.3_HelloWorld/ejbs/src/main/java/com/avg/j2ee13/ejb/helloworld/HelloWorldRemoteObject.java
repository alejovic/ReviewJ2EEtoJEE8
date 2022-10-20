package com.avg.j2ee13.ejb.helloworld;

import java.rmi.RemoteException;

/**
 * The remote component interface extends javax.ejb.EJBObject
 * <p>
 * Implementing the Remote Component Interface;
 * The remote interface defines the business methods that a remote client can invoke. The requirements for developing
 * the remote component interface include:
 * - The remote component interface of the bean must extend the javax.ejb.EJBObject interface, and its methods must
 * throw the java.rmi.RemoteException exception.
 * - You must declare the remote interface and its methods as public for remote clients.
 * - The remote component interface, all its method parameters, and return types must be serializable. In general,
 * any object that is passed between the client and the enterprise bean must be serializable,
 * because RMI marshals and unmarshalls the object on both ends.
 * - Any exception can be thrown to the client. Run-time exceptions, including EJBException and RemoteException,
 * are transferred back to the client as remote run-time exceptions.
 * - A remote component interface can throw a specified application exceptions.
 * <p>
 * The EJBObject does not have persistent state.
 */

/**
 * Remote Component Interface for HelloWorld which defines Business Methods
 */
public interface HelloWorldRemoteObject extends javax.ejb.EJBObject {

    public String sayHello(String myName) throws RemoteException;

    public String sayHello() throws RemoteException;
}

