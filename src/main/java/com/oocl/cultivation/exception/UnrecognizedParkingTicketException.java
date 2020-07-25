package com.oocl.cultivation.exception;

public class UnrecognizedParkingTicketException extends Exception {

    public UnrecognizedParkingTicketException() {
        super("Unrecognized parking ticket.");
    }
}
