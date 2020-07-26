package com.oocl.cultivation.entity;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public ParkingLot findWillBeParkedParkingLot() {
        return parkingLots.stream()
                .min(Comparator.comparing(parkingLot -> parkingLot.getCars().size()))
                .orElse(null);
    }
}
