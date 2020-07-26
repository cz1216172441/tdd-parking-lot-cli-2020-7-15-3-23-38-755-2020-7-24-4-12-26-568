package com.oocl.cultivation.entity;

import java.util.Objects;
import java.util.UUID;

public class Ticket {

    private final String carId;

    private final String parkingLotId;

    private final String signature;

    public Ticket(String carId, String parkingLotId) {
        this.carId = carId;
        this.parkingLotId = parkingLotId;
        this.signature = this.createSignature();
    }

    private String createSignature() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getCarId() {
        return carId;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return carId.equals(ticket.carId) &&
                parkingLotId.equals(ticket.parkingLotId) &&
                signature.equals(ticket.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, parkingLotId, signature);
    }
}
