package com.spring.basics.exception;

public class PaginationException extends RuntimeException
{
    public PaginationException(String message)
    {
        super(message);
    }
}
