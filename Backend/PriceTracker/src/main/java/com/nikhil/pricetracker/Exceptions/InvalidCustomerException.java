package com.nikhil.pricetracker.Exceptions;

public class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(){
        super("Email is mandatory");
    }
}
