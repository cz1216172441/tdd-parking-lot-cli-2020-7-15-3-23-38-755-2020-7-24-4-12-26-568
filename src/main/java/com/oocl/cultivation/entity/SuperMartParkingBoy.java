package com.oocl.cultivation.entity;

import java.util.Comparator;

public class SuperMartParkingBoy extends ParkingBoy {

    @Override
    public ParkingLot findWillBeParkedParkingLot() {
        return parkingLots.stream()
                .max(Comparator.comparing(parkingLot -> parkingLot.getCars().size() / ParkingLot.CAPACITY))
                .orElse(null);
    }
}
