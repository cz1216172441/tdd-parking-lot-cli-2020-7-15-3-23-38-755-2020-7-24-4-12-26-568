package com.oocl.cultivation.entity;

public class ParkingBoy {

    public Ticket parkingCar(Car car) {
        return new Ticket(car.getId());
    }

    public Car fetchingCar(Ticket ticket) {
        return new Car(ticket.getNumber());
    }

}
