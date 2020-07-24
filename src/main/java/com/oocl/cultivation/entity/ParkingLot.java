package com.oocl.cultivation.entity;

import java.util.List;

public class ParkingLot {

    private final List<Car> cars;

    public ParkingLot(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
