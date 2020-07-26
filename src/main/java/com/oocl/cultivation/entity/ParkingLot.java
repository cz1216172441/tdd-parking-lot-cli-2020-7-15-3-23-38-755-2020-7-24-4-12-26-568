package com.oocl.cultivation.entity;

import com.oocl.cultivation.exception.NotEnoughPositionException;

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

    public Ticket parking(Car car) throws NotEnoughPositionException {
        if (cars.size() < CAPACITY) {
            cars.add(car);
            Ticket ticket = new Ticket(car.getId(), this.id);
            this.tickets.add(ticket);
            return ticket;
        }
        throw new NotEnoughPositionException();
    }
}
