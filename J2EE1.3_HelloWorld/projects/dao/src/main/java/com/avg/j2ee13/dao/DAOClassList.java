package com.avg.j2ee13.dao;

public class DAOClassList {

    public static final DAOClassList HelloWorld = new DAOClassList("HelloWorld");
    public static final DAOClassList DaoClass2 = new DAOClassList("DaoClass2");

    final String daoClass;

    private DAOClassList(String daoClass) {
        this.daoClass = daoClass;
    }

    public String toString() {
        return this.daoClass;
    }
}

