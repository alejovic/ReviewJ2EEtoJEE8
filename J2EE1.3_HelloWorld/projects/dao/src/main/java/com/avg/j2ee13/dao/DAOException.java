package com.avg.j2ee13.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;


public class DAOException extends Exception implements Serializable {

    private transient Log logger = LogFactory.getLog(this.getClass());

    public final static String ERROR_DAO_01 = "ERROR_DAO_01";

    private String code;
    private Throwable cause;

    public DAOException(String code, String message) {
        this(code, message, null);
    }

    public DAOException(String code, String message, Throwable cause) {
        super(code + " : " + message);
        this.code = code;
        this.cause = cause;
        if (code != null) {
            logger.error(cause);
        } else {
            logger.error(this);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

}
