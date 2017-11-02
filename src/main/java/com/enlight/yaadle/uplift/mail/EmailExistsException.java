package com.enlight.yaadle.uplift.mail;

@SuppressWarnings("serial")
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}