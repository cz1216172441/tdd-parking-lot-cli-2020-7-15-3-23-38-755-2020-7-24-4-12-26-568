package com.oocl.cultivation.entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final String id;

    private final List<Car> cars;

    private final List<Ticket> tickets;

    public static final int CAPACITY = 10;

    public ParkingLot(String id) {
        this.id = id;
        cars = new ArrayList<>(CAPACITY);
        tickets = new ArrayList<>(CAPACITY);
    }

    public String getId() {
        return id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
