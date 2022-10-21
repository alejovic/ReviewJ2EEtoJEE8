package com.avg.j2ee13.util.localization;

import com.avg.j2ee13.util.BaseException;

public class LocalizationException extends BaseException {

    public LocalizationException(String message, Exception exception) {
        super(message, exception);
    }

    public LocalizationException(String message) {
        super(message);
    }

    public LocalizationException(Exception exception) {
        super(exception);
    }
}
