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
            ParkingLot resultParkingLot = findWillBeParkedParkingLot();
            if (resultParkingLot != null) {
                return resultParkingLot.parking(car);
            }
        }
        throw new NotEnoughPositionException();
    }

    public ParkingLot findWillBeParkedParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getCars().size() < ParkingLot.CAPACITY)
                .findFirst()
                .orElse(null);
    }

    public Car fetchingCar(Ticket ticket) throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        ParkingLot resultParkingLot = findWillBeFetchedParkingLot(ticket);
        if (resultParkingLot != null) {
            return resultParkingLot.fetching(ticket);
        }
        throw new UnrecognizedParkingTicketException();
    }

    public ParkingLot findWillBeFetchedParkingLot(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getId().equals(ticket.getParkingLotId()) && parkingLot.getCars().size() > 0)
                .findFirst()
                .orElse(null);
    }

}
