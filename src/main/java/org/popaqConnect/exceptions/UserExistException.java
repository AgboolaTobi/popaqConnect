package org.popaqConnect.exceptions;

import org.popaqConnect.exceptions.PopaqConnectException;

public class UserExistException extends PopaqConnectException {
    public UserExistException(String message) {
        super(message);
    }
}
