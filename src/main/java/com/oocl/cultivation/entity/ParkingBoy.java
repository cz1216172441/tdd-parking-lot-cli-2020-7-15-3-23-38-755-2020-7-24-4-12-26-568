package com.oocl.cultivation.entity;

import com.oocl.cultivation.exception.NoProvideParkingTicketException;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    protected final List<ParkingLot> parkingLots;

    public ParkingBoy() {
        parkingLots = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Ticket parkingCar(Car car) throws NotEnoughPositionException {
        if (!parkingLots.isEmpty()) {
            Optional<ParkingLot> resultParkingLot = findWillBeParkedParkingLot();
            if (resultParkingLot.isPresent()) {
                return resultParkingLot.get().parking(car);
            }
        }
        throw new NotEnoughPositionException();
    }

    public Optional<ParkingLot> findWillBeParkedParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getCars().size() < ParkingLot.CAPACITY)
                .findFirst();
    }

    public Car fetchingCar(Ticket ticket) throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        if (ticket == null) {
            throw new NoProvideParkingTicketException();
        }
        Optional<ParkingLot> resultParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getId().equals(ticket.getParkingLotId()) && parkingLot.getCars().size() > 0)
                .findFirst();
        if (resultParkingLot.isPresent()) {
            return resultParkingLot.get().fetching(ticket);
        }
        throw new UnrecognizedParkingTicketException();
    }

}
