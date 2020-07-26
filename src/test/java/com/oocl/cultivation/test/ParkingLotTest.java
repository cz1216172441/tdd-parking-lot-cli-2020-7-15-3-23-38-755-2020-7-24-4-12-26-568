package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NoProvideParkingTicketException;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
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

    @Test
    void should_return_1_car_when_fetching_given_1_ticket() throws UnrecognizedParkingTicketException,
            NoProvideParkingTicketException, NotEnoughPositionException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT1");
        parkingLot.parking(new Car("CAR001"));
        // when
        Car car = parkingLot.fetching(ticket);
        // then
        Assertions.assertEquals("CAR001", car.getId());
    }

}
