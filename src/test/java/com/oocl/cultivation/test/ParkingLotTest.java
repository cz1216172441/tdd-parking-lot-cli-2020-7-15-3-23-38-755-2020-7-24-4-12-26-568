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

    @Test
    void should_return_2_ticket_when_parking_given_2_car() throws NotEnoughPositionException {
        // given
        Car car001 = new Car("CAR001");
        Car car002 = new Car("CAR002");
        // when
        Ticket ticket1 = parkingLot.parking(car001);
        Ticket ticket2 = parkingLot.parking(car002);
        // then
        Assertions.assertEquals("CAR001", ticket1.getCarId());
        Assertions.assertEquals("PARKINGLOT1", ticket1.getParkingLotId());
        Assertions.assertEquals("CAR002", ticket2.getCarId());
        Assertions.assertEquals("PARKINGLOT1", ticket1.getParkingLotId());
    }

    @Test
    void should_return_1_correct_car_when_fetching_given_1_ticket_and_1_parking_lot_with_2_car() throws NotEnoughPositionException, UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT1");
        parkingLot.parking(new Car("CAR001"));
        parkingLot.parking(new Car("CAR002"));
        // when
        Car correctCar = parkingLot.fetching(ticket);
        // then
        Assertions.assertEquals("CAR001", correctCar.getId());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_given_1_wrong_ticket() throws NotEnoughPositionException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT1");
        parkingLot.parking(new Car("CAR002"));
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetching(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_given_a_null_number_ticket() {
        // given
        Ticket ticket = new Ticket(null, null);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetching(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
