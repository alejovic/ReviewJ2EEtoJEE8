package com.avg.j2ee13.dao;

public final class DAOParameters {
    public static final String PARAM_SERVICE_LOCATOR = "SERVICE_LOCATOR";
    public static final String PARAM_DAO_DATASOURCE = "DAO_DATASOURCE";
    public static final int FACTORY_LDAP = 1;
    public static final int FACTORY_BD = 2;
    public static final int FACTORY_MEMORY = 3;
    public static final int FACTORY_FILE_STORE = 4;

    private DAOParameters() {
    }
}
