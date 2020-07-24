package com.oocl.cultivation.entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final List<Car> cars;

    public ParkingLot() {
        cars = new ArrayList<>(10);
    }

    public List<Car> getCars() {
        return cars;
    }
}
