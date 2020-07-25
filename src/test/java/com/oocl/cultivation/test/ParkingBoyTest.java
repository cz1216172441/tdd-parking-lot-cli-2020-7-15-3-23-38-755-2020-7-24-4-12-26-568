package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingBoy;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NoProvideParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    private ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy();
        parkingBoy.setParkingLot(parkingLot);
    }

    @Test
    void should_return_1_ticket_when_parking_car_given_1_car_and_1_parking_boy() {
        // given
        Car car = new Car("CAR001");
        // when
        Ticket ticket = parkingBoy.parkingCar(car);
        // then
        Assertions.assertEquals("CAR001", ticket.getNumber());
    }

    @Test
    void should_return_1_car_when_fetching_car_given_1_ticker_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001");
        parkingLot.getCars().add(new Car("CAR001"));
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", car.getId());
    }

    @Test
    void should_return_2_ticket_when_parking_car_given_2_car_and_1_packing_boy() {
        // given
        Car car001 = new Car("CAR001");
        Car car002 = new Car("CAR002");
        // when
        Ticket ticket1 = parkingBoy.parkingCar(car001);
        Ticket ticket2 = parkingBoy.parkingCar(car002);
        // then
        Assertions.assertEquals("CAR001", ticket1.getNumber());
        Assertions.assertEquals("CAR002", ticket2.getNumber());
    }

    @Test
    void should_return_1_correct_car_when_fetching_car_given_1_ticket_and_1_packing_boy_and_1_parking_lot_with_2_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001");
        parkingLot.getCars().add(new Car("CAR001"));
        parkingLot.getCars().add(new Car("CAR002"));
        // when
        Car correctCar = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", correctCar.getId());
    }

    @Test
    void should_return_no_car_when_fetching_car_given_1_wrong_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001");
        parkingLot.getCars().add(new Car("CAR002"));
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_please_provide_your_parking_ticket_when_fetching_car_given_no_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        parkingLot.getCars().add(new Car("CAR002"));
        // when
        Exception exception = Assertions.assertThrows(NoProvideParkingTicketException.class, () -> parkingBoy.fetchingCar(null));
        // then
        Assertions.assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_1_used_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001");
        parkingLot.getCars().add(new Car("CAR001"));
        parkingBoy.fetchingCar(ticket);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetchingCar(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


    @Test
    void should_return_can_not_parking_when_parking_car_given_1_car_and_1_parking_boy_and_1_capacity_is_10_parking_lot_with_10_car() {
        // given
        Car car = new Car("CAR11");
        List<Car> cars = parkingLot.getCars();
        for (int i = 1; i <= 10; i++) {
            cars.add(new Car(String.format("CAR%d", i)));
        }
        // when
        Ticket ticket = parkingBoy.parkingCar(car);
        // then
        Assertions.assertNull(ticket);
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_a_null_number_ticket() {
        // given
        Ticket ticket = new Ticket(null);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetchingCar(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_a_used_ticket() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Car car = new Car("CAR001");
        Ticket ticket = parkingBoy.parkingCar(car);
        parkingBoy.fetchingCar(ticket);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetchingCar(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_please_provide_your_parking_ticket_when_fetching_car_given_null_ticket() {
        // given
        // when
        Exception exception = Assertions.assertThrows(NoProvideParkingTicketException.class, () -> parkingBoy.fetchingCar(null));
        // then
        Assertions.assertEquals("Please provide your parking ticket.", exception.getMessage());
    }
}
