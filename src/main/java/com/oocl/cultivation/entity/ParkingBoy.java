package com.oocl.cultivation.entity;

public class ParkingBoy {

    private final int id;

    public ParkingBoy(int id) {
        this.id = id;
    }

    public String parkingCar(Car car) {
        return car.getId();
    }

    public Car fetchingCar(String ticket) {
        return new Car(ticket);
    }

}
