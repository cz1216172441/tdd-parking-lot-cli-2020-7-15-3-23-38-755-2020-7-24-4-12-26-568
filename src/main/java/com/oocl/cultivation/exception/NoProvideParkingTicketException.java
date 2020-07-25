package com.oocl.cultivation.exception;

public class NoProvideParkingTicketException extends Exception {

    public NoProvideParkingTicketException() {
        super("Please provide your parking ticket.");
    }
}
