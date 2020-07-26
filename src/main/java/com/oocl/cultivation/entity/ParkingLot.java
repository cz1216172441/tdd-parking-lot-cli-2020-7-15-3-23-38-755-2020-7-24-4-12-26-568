package com.oocl.cultivation.entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final List<Car> cars;

    public static final int CAPACITY = 10;

    public ParkingLot() {
        cars = new ArrayList<>(CAPACITY);
    }

    public List<Car> getCars() {
        return cars;
    }
}
