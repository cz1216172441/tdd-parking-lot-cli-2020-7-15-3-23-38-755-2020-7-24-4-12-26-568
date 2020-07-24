package com.oocl.cultivation.entity;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkingCar(Car car) {
        return new Ticket(car.getId());
    }

    public Car fetchingCar(Ticket ticket) {
        return new Car(ticket.getNumber());
    }

}
