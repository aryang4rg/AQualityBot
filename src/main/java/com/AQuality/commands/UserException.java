package com.AQuality.commands;

//todo throw this exception more through out the code
/**
 * Exception thrown when the user inputted something incorrect / faulty
 */
public class UserException extends Exception
{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
