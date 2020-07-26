package com.oocl.cultivation.entity;

import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Optional<ParkingLot> findWillBeParkedParkingLot() {
        return parkingLots.stream()
                .min(Comparator.comparing(parkingLot -> parkingLot.getCars().size()));
    }
}
