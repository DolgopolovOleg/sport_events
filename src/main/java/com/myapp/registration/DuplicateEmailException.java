package com.myapp.registration;

public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
