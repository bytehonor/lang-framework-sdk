package com.bytehonor.sdk.lang.spring.exception;

/**
 * @author lijianqiang
 *
 */
public class LangSdkException extends RuntimeException {

    private static final long serialVersionUID = 7031815564816223750L;

    public LangSdkException() {
        super();
    }

    public LangSdkException(String message) {
        super(message);
    }

    public LangSdkException(String message, Throwable cause) {
        super(message, cause);
    }
}
