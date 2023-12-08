package com.nikhil.pricetracker.Service;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String plainText, String hashed);
}
