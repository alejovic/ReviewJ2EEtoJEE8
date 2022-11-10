package com.avg.j2ee13.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

public class ClientException extends Exception implements Serializable {

    private final transient Log logger = LogFactory.getLog(this.getClass());

    public ClientException(String code, String message) {
        this(code, message, null);
    }

    public ClientException(String code, String message, Throwable cause) {
        super(code + " : " + message);
        if (code != null) {
            logger.error(cause);
        } else {
            logger.error(this);
        }
    }

}
