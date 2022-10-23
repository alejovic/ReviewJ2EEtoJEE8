package com.avg.j2ee13.dao;

public interface DAOParameters {

    String SERVICE_LOCATOR = "SERVICE_LOCATOR";
    String DAO_DATASOURCE = "DAO_DATASOURCE";

    int LDAP = 1;
    int ORACLE = 2;
    int MEMORY = 3;
    int FILE_STORE = 4;
}
