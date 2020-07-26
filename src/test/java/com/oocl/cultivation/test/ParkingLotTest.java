package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot("PARKINGLOT1");
    }

    @Test
    void should_return_1_ticket_when_parking_given_1_car() throws NotEnoughPositionException {
        // given
        Car car = new Car("CAR001");
        // when
        Ticket ticket = parkingLot.parking(car);
        // then
        Assertions.assertEquals("CAR001", ticket.getCarId());
        Assertions.assertEquals("PARKINGLOT1", ticket.getParkingLotId());
    }
}
