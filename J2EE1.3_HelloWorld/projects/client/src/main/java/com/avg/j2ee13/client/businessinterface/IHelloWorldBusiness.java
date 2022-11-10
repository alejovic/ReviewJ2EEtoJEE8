package com.avg.j2ee13.client.businessinterface;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.client.ClientException;

/**
 * BusinessService Interface
 */
public interface IHelloWorldBusiness extends IGenericBusiness {

    String sayHello(String myName) throws ClientException;

    String sayHello() throws ClientException;

}
