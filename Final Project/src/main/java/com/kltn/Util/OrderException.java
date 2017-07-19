package com.kltn.Util;

/**
 * Created by TinNguyen on 7/19/17.
 */
public class OrderException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public OrderException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public OrderException() {
        super();
    }
}
