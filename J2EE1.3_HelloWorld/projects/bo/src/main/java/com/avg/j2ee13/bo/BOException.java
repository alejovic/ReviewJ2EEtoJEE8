package com.avg.j2ee13.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;


public class BOException extends Exception implements Serializable {

    public static final String BO_TRANSFER_ERROR = "BO_TRANSFER_ERROR";
    public static final String BO_ERROR_01 = "BO_ERROR_01";

    private final transient Log logger = LogFactory.getLog(this.getClass());

    public BOException(String code, String message) {
        this(code, message, null);
    }

    public BOException(String code, String message, Throwable cause) {
        super(code + " : " + message);
        if (code != null) {
            logger.error(cause);
        } else {
            logger.error(this);
        }
    }

}
