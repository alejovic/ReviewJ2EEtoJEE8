package com.avg.j2ee13.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;


public class DAOException extends Exception implements Serializable {

    public static final String DAO_INSTANCE_CLASS = "DAO_INSTANCE_CLASS";
    public static final String DAO_FACTORY_CLASS = "DAO_FACTORY_CLASS";
    public static final String DAO_MISSING_PARAMETER = "DAO_MISSING_PARAMETER";
    public static final String DAO_NO_FILE = "DAO_NO_FILE";
    public static final String DAO_ERROR_FILE_SYNC = "DAO_ERROR_FILE_SYNC";
    public static final String DAO_ERROR_CONNECTION = "DAO_ERROR_CONNECTION";
    public static final String DAO_ERROR_FIND_ALL = "DAO_ERROR_FIND_ALL";
    public static final String DAO_ERROR_FIND = "DAO_ERROR_FIND";
    public static final String DAO_ERROR_INSERT = "DAO_ERROR_INSERT";
    public static final String DAO_ERROR_UPDATE = "DAO_ERROR_UPDATE";
    public static final String DAO_ERROR_DELETE = "DAO_ERROR_DELETE";
    private final transient Log logger = LogFactory.getLog(this.getClass());

    public DAOException(String code, String message) {
        this(code, message, null);
    }

    public DAOException(String code, String message, Throwable cause) {
        super(code + " : " + message);
        if (code != null) {
            logger.error(cause);
        } else {
            logger.error(this);
        }
    }

}
