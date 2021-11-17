package com.bytehonor.sdk.lang.bytehonor.exception;

/**
 * @author lijianqiang
 *
 */
public class BytehonorLangException extends RuntimeException {

    private static final long serialVersionUID = 7031815564816223750L;

    public BytehonorLangException() {
        super();
    }

    public BytehonorLangException(String message) {
        super(message);
    }

    public BytehonorLangException(String message, Throwable cause) {
        super(message, cause);
    }
}
