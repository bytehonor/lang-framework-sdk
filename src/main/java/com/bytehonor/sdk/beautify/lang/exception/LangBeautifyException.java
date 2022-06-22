package com.bytehonor.sdk.beautify.lang.exception;

/**
 * @author lijianqiang
 *
 */
public class LangBeautifyException extends RuntimeException {

    private static final long serialVersionUID = 7031815564816223750L;

    public LangBeautifyException() {
        super();
    }

    public LangBeautifyException(String message) {
        super(message);
    }

    public LangBeautifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
