package com.bytehonor.sdk.lang.spring.exception;

/**
 * @author lijianqiang
 *
 */
public class SpringLangException extends RuntimeException {

    private static final long serialVersionUID = 7031815564816223750L;

    public SpringLangException() {
        super();
    }

    public SpringLangException(String message) {
        super(message);
    }

    public SpringLangException(String message, Throwable cause) {
        super(message, cause);
    }
}
