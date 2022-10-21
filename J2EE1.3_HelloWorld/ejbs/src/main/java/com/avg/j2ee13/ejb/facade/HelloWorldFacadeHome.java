package com.avg.j2ee13.ejb.facade;

import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * Implementing the Remote Home Interface
 * A remote client invokes the EJB through its remote interface. The client invokes the create method that is declared
 * within the remote home interface. The container passes the client call to the ejbCreate method–with the appropriate parameter signature–within the bean implementation. The requirements for developing the remote home interface include the following:
 * <p>
 * - The remote home interface must extend the javax.ejb.EJBHome interface.
 * <p>
 * - All create methods may throw the following exceptions:
 * <p>
 * -javax.ejb.CreateException
 * <p>
 * -javax.ejb.EJBException or another RuntimeException
 */

/**
 * Remote Home Interface for a HelloWorld Stateless Session Bean.
 */
public interface HelloWorldFacadeHome extends javax.ejb.EJBHome {

    public HelloWorldFacade create() throws RemoteException, CreateException;

}
