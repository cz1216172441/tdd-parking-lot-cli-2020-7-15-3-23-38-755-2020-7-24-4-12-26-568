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

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_given_a_used_ticket() throws NotEnoughPositionException, UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Car car = new Car("CAR001");
        Ticket ticket = parkingLot.parking(car);
        parkingLot.fetching(ticket);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetching(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_please_provide_your_parking_ticket_when_fetching_given_null_ticket() {
        // given
        // when
        Exception exception = Assertions.assertThrows(NoProvideParkingTicketException.class, () -> parkingLot.fetching(null));
        // then
        Assertions.assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_not_enough_position_when_parking_given_a_car_and_a_full_capacity_parking_lot() throws NotEnoughPositionException {
        // given
        Car car = new Car("CAR11");
        for (int i = 1; i <= ParkingLot.CAPACITY; i++) {
            parkingLot.parking(new Car(String.format("CAR%d", i)));
        }
        // when
        Exception exception = Assertions.assertThrows(NotEnoughPositionException.class, () -> parkingLot.parking(car));
        // then
        Assertions.assertEquals("Not enough position.", exception.getMessage());
    }
}
