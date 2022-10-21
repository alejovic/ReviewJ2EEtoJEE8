package com.avg.j2ee13.ejb.facade;

import com.avg.j2ee13.bo.interfaces.IHelloWorldBusiness;
import com.avg.j2ee13.util.localization.PropertiesConfiguration;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Bean Name="HelloWorld"
 * description="An EJB named HelloWorld"
 * display-name="HelloWorld"
 * jndi-name="HelloWorld"
 * type="Stateless"
 * transaction-type="Container"
 */
public abstract class HelloWorldFacadeBean extends GenericFacadeBean implements  IHelloWorldBusiness {

}
