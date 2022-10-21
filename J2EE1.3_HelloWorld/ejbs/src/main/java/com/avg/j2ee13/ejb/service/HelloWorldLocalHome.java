package com.avg.j2ee13.ejb.service;

import javax.ejb.CreateException;

/**
 * Implementing the Local Home Interface
 * An EJB can be called locally from a client that exists in the same container. Thus, a collocated bean, JSP, or
 * servlet invokes the create method that is declared within the local home interface. The container passes the client
 * call to the ejbCreate method–with the appropriate parameter signature–within the bean implementation.
 * <p>
 * The requirements for developing the local home interface include the following:
 * <p>
 * - The local home interface must extend the javax.ejb.EJBLocalHome interface.
 * <p>
 * - All create methods may throw the following exceptions:
 * <p>
 * - javax.ejb.CreateException
 * <p>
 * - javax.ejb.EJBException or another RuntimeException
 */

/**
 * Local Home Interface for HelloWorld a HelloWorld Stateless Session Bean.
 */
public interface HelloWorldLocalHome extends javax.ejb.EJBLocalHome {

    public HelloWorldLocal create() throws CreateException;

}
