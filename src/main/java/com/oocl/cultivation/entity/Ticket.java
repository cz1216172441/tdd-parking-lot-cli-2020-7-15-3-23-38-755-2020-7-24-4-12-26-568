package com.oocl.cultivation.entity;

public class Ticket {

    private final String carId;

    private final String parkingLotId;

    public Ticket(String carId, String parkingLotId) {
        this.carId = carId;
        this.parkingLotId = parkingLotId;
    }

    public String getCarId() {
        return carId;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }
}
