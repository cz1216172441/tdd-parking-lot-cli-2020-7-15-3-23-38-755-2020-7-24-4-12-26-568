package com.oocl.cultivation.entity;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkingCar(Car car) {
        return new Ticket(car.getId());
    }

    public Car fetchingCar(Ticket ticket) {
        if (parkingLot != null) {
            List<Car> cars = parkingLot.getCars();
            if (cars != null && !cars.isEmpty()) {
                Optional<Car> first = cars.stream()
                        .filter(car -> car.getId().equals(ticket.getNumber()))
                        .findFirst();
                if (first.isPresent()) {
                    return new Car(first.get().getId());
                }
            }
        }
        return null;
    }

}
