package com.oocl.cultivation.entity;

import com.oocl.cultivation.exception.NoProvideParkingTicketException;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkingCar(Car car) throws NotEnoughPositionException {
        List<Car> cars = parkingLot.getCars();
        if (cars.size() < ParkingLot.CAPACITY) {
            cars.add(car);
            return new Ticket(car.getId());
        } else {
            throw new NotEnoughPositionException();
        }
    }

    public Car fetchingCar(Ticket ticket) throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        if (ticket == null) {
            throw new NoProvideParkingTicketException();
        }
        if (parkingLot != null) {
            List<Car> cars = parkingLot.getCars();
            if (ticket.getNumber() == null) {
                throw new UnrecognizedParkingTicketException();
            }
            if (!cars.isEmpty()) {
                Optional<Car> resultCar = cars.stream()
                        .filter(car -> car.getId().equals(ticket.getNumber()))
                        .findFirst();
                if (resultCar.isPresent()) {
                    String carId = resultCar.get().getId();
                    cars.removeIf(car -> car.getId().equals(carId));
                    return new Car(carId);
                }
            } else {
                throw new UnrecognizedParkingTicketException();
            }
        }
        return null;
    }

}
