package com.bntech.googleintegrator.manager.exception;

public class ShorthandNotFoundException extends RuntimeException {
    public ShorthandNotFoundException() {
        super("Shorthand not found");
    }
}
