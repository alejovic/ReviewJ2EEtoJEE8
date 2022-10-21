package com.avg.j2ee13.util;

import java.io.Serializable;

public abstract class BaseException extends Exception implements Serializable {

    private Exception exception;

    /**
     * Creates a new LocalizationException wrapping another exception, and with a detail message.
     *
     * @param message   the detail message.
     * @param exception the wrapped exception.
     */
    public BaseException(String message, Exception exception) {
        super(message);
        this.exception = exception;
        return;
    }

    /**
     * Creates a LocalizationException with the specified detail message.
     *
     * @param message the detail message.
     */
    public BaseException(String message) {
        this(message, null);
        return;
    }

    /**
     * Creates a new LocalizationException wrapping another exception, and with no detail message.
     *
     * @param exception the wrapped exception.
     */
    public BaseException(Exception exception) {
        this(null, exception);
        return;
    }

    /**
     * Gets the wrapped exception.
     *
     * @return the wrapped exception.
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Retrieves (recursively) the root cause exception.
     *
     * @return the root cause exception.
     */
    public Exception getRootCause() {
        if (exception instanceof BaseException) {
            return ((BaseException) exception).getRootCause();
        }
        return exception == null ? this : exception;
    }

    public String toString() {
        if (exception instanceof BaseException) {
            return ((BaseException) exception).toString();
        }
        return exception == null ? super.toString() : exception.toString();
    }
}
