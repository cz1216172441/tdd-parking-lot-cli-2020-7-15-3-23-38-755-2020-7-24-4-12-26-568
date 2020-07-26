package com.oocl.cultivation.entity;

import com.oocl.cultivation.exception.NotEnoughPositionException;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket parkingCar(Car car) throws NotEnoughPositionException {
        return super.parkingCar(car);
    }
}
