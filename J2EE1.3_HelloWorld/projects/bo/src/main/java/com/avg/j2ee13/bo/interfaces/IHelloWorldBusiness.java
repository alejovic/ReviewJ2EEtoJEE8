package com.avg.j2ee13.bo.interfaces;

/**
 * BusinessService Interface
 */
public interface IHelloWorldBusiness extends IGenericBusiness {

    public String sayHello(String myName);

    public String sayHello();

}
