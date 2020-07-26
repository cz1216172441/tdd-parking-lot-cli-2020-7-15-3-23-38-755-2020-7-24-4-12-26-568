package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.*;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    @BeforeEach
    public void setUp() {
        parkingBoy = new ParkingBoy();
    }

    @Test
    void should_parked_parking_lot_2_when_parking_car_given_a_car_and_parking_lot_1_no_position_and_lot_2_has_position() throws NotEnoughPositionException {
        // given
        ParkingLot parkingLot1 = new ParkingLot("PARKINGLOT1");
        parkingBoy.addParkingLot(parkingLot1);
        for (int i = 1; i <= ParkingLot.CAPACITY; i++) {
            parkingLot1.parking(new Car(String.format("CAR%d", i)));
        }
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot2.getId()).thenReturn("PARKINGLOT2");
        parkingBoy.addParkingLot(parkingLot2);
        Car car = new Car("CAR11");
        // when
        when(parkingLot2.parking(car)).thenReturn(new Ticket("CAR11", "PARKINGLOT2"));
        ParkingLot resultParkingLot = parkingBoy.findWillBeParkedParkingLot();
        Ticket ticket = parkingLot2.parking(car);
        // then
        assertEquals(ticket.getParkingLotId(), resultParkingLot.getId());
    }

}
