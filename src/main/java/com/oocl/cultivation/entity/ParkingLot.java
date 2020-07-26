package com.oocl.cultivation.entity;

import com.oocl.cultivation.exception.NoProvideParkingTicketException;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Car fetching(Ticket ticket) throws NoProvideParkingTicketException, UnrecognizedParkingTicketException {
        if (ticket == null) {
            throw new NoProvideParkingTicketException();
        }
        boolean isTicketExisted = tickets.removeIf(ticketItem -> ticketItem.equals(ticket));
        if (isTicketExisted) {
            Optional<Car> resultCar = cars.stream().filter(car -> car.getId().equals(ticket.getCarId())).findFirst();
            if (resultCar.isPresent()) {
                cars.removeIf(car -> car.getId().equals(ticket.getCarId()));
                return resultCar.get();
            }
        }
        throw new UnrecognizedParkingTicketException();
    }
}
