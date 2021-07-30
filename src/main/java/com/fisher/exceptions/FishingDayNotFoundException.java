package com.fisher.exceptions;

public class FishingDayNotFoundException extends RuntimeException{

    public FishingDayNotFoundException(String message) {
        super(message);
    }
}
