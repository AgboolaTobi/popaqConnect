package org.popaqConnect.exceptions;

public class AdminAlreadyExists extends RuntimeException {
    public AdminAlreadyExists(String message){
        super(message);
    }
}
