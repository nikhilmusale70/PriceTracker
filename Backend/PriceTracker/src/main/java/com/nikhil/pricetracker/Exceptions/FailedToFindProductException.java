package com.nikhil.pricetracker.Exceptions;

public class FailedToFindProductException extends RuntimeException{
    public FailedToFindProductException(String productCouldNotBeFound) {
        super(productCouldNotBeFound);
    }
}
