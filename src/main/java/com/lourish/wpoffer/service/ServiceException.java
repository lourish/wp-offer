package com.lourish.wpoffer.service;

/**
 * Wrapper for service-level checked exceptions
 *
 * @author dave
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1597698224217998668L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
