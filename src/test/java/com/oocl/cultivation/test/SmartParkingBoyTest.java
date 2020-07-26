package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.SmartParkingBoy;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SmartParkingBoyTest {

    @Test
    void should_parked_lot_2_when_parking_car_given_parking_lot_1_parking_9_car_and_parking_lot_2_parking_no_car() throws NotEnoughPositionException {
        // given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        ParkingLot parkingLot1 = new ParkingLot("PARKINGLOT1");
        for (int i = 1; i < ParkingLot.CAPACITY; i++) {
            parkingLot1.parking(new Car(String.format("CAR%d", i)));
        }
        smartParkingBoy.addParkingLot(parkingLot1);

        Car car = new Car("CAR10");

        ParkingLot parkingLot2 = mock(ParkingLot.class);
        smartParkingBoy.addParkingLot(parkingLot2);

        when(parkingLot2.getId()).thenReturn("PARKINGLOT2");
        when(parkingLot2.parking(car)).thenReturn(new Ticket("CAR10", "PARKINGLOT2"));

        // when
        ParkingLot resultParkingLot = smartParkingBoy.findWillBeParkedParkingLot();
        Ticket ticket = parkingLot2.parking(car);

        // then
        assertEquals(ticket.getParkingLotId(), resultParkingLot.getId());
    }
}
