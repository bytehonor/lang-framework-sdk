package com.bytehonor.sdk.framework.lang.exception;

/**
 * SDK 内使用的通用非受检异常（运行时异常）。
 *
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
