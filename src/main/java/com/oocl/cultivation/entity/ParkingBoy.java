package com.oocl.cultivation.entity;

public class ParkingBoy {

    private final int id;

    public ParkingBoy(int id) {
        this.id = id;
    }

    public Ticket parkingCar(Car car) {
        return new Ticket(car.getId());
    }

    public Car fetchingCar(String ticket) {
        return new Car(ticket);
    }

}
