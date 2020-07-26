package com.oocl.cultivation.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return carId.equals(ticket.carId) &&
                parkingLotId.equals(ticket.parkingLotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, parkingLotId);
    }
}
