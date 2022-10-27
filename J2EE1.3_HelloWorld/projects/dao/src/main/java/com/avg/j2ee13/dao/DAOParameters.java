package com.avg.j2ee13.dao;

public final class DAOParameters {
    private DAOParameters() {
    }

    public static final String SERVICE_LOCATOR = "SERVICE_LOCATOR";
    public static final String DAO_DATASOURCE = "DAO_DATASOURCE";

    public static final int LDAP = 1;
    public static final int ORACLE = 2;
    public static final int MEMORY = 3;
    public static final int FILE_STORE = 4;
}
