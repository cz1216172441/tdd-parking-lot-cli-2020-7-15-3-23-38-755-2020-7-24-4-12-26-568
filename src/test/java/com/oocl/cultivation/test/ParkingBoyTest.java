package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingBoy;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NoProvideParkingTicketException;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    private ParkingLot parkingLot1;

    @BeforeEach
    public void setUp() {
        parkingLot1 = new ParkingLot("PARKINGLOT001");
        parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
    }

    @Test
    void should_return_1_ticket_when_parking_car_given_1_car_and_1_parking_boy() throws NotEnoughPositionException {
        // given
        Car car = new Car("CAR001");
        // when
        Ticket ticket = parkingBoy.parkingCar(car);
        // then
        Assertions.assertEquals("CAR001", ticket.getCarId());
        Assertions.assertEquals("PARKINGLOT001", ticket.getParkingLotId());
    }

    @Test
    void should_return_1_car_when_fetching_car_given_1_ticker_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT001");
        parkingLot1.getCars().add(new Car("CAR001"));
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", car.getId());
    }

    @Test
    void should_return_2_ticket_when_parking_car_given_2_car_and_1_packing_boy() throws NotEnoughPositionException {
        // given
        Car car001 = new Car("CAR001");
        Car car002 = new Car("CAR002");
        // when
        Ticket ticket1 = parkingBoy.parkingCar(car001);
        Ticket ticket2 = parkingBoy.parkingCar(car002);
        // then
        Assertions.assertEquals("CAR001", ticket1.getCarId());
        Assertions.assertEquals("PARKINGLOT001", ticket1.getParkingLotId());
        Assertions.assertEquals("CAR002", ticket2.getCarId());
        Assertions.assertEquals("PARKINGLOT001", ticket1.getParkingLotId());
    }

    @Test
    void should_return_1_correct_car_when_fetching_car_given_1_ticket_and_1_packing_boy_and_1_parking_lot_with_2_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT001");
        parkingLot1.getCars().add(new Car("CAR001"));
        parkingLot1.getCars().add(new Car("CAR002"));
        // when
        Car correctCar = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", correctCar.getId());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_1_wrong_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT001");
        parkingLot1.getCars().add(new Car("CAR002"));
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetchingCar(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_please_provide_your_parking_ticket_when_fetching_car_given_no_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        parkingLot1.getCars().add(new Car("CAR002"));
        // when
        Exception exception = Assertions.assertThrows(NoProvideParkingTicketException.class, () -> parkingBoy.fetchingCar(null));
        // then
        Assertions.assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_1_used_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException {
        // given
        Ticket ticket = new Ticket("CAR001", "PARKINGLOT001");
        parkingLot1.getCars().add(new Car("CAR001"));
        parkingBoy.fetchingCar(ticket);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetchingCar(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


    @Test
    void should_return_not_enough_position_when_parking_car_given_1_car_and_1_parking_boy_and_1_capacity_is_10_parking_lot_with_10_car() throws NotEnoughPositionException {
        // given
        Car car = new Car("CAR11");
        List<Car> cars = parkingLot1.getCars();
        for (int i = 1; i <= ParkingLot.CAPACITY; i++) {
            cars.add(new Car(String.format("CAR%d", i)));
        }
        // when
        Exception exception = Assertions.assertThrows(NotEnoughPositionException.class, () -> parkingBoy.parkingCar(car));
        // then
        Assertions.assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_a_null_number_ticket() {
        // given
        Ticket ticket = new Ticket(null, null);
        // when
        Exception exception = Assertions.assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetchingCar(ticket));
        // then
        Assertions.assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetching_car_given_a_used_ticket() throws UnrecognizedParkingTicketException, NoProvideParkingTicketException, NotEnoughPositionException {
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

    @Test
    void should_return_not_enough_position_when_parking_car_given_a_car_and_a_full_capacity_parking_lot() {
        // given
        Car car = new Car("CAR11");
        for (int i = 1; i <= ParkingLot.CAPACITY; i++) {
            parkingLot1.getCars().add(new Car(String.format("CAR%d", i)));
        }
        // when
        Exception exception = Assertions.assertThrows(NotEnoughPositionException.class, () -> parkingBoy.parkingCar(car));
        // then
        Assertions.assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    void should_return_ticket_with_parking_lot_2_when_parking_car_given_a_car_and_2_parking_lot_and_one_of_the_parking_lot_is_full_capacity() throws NotEnoughPositionException {
        // given
        Car car = new Car("CAR11");
        ParkingLot parkingLot2 = new ParkingLot("PARKINGLOT002");
        parkingBoy.addParkingLot(parkingLot2);
        for (int i = 1; i <= ParkingLot.CAPACITY; i++) {
            parkingLot1.getCars().add(new Car(String.format("CAR%d", i)));
        }
        // when
        Ticket ticket = parkingBoy.parkingCar(car);
        // then
        Assertions.assertEquals("CAR11", ticket.getCarId());
        Assertions.assertEquals("PARKINGLOT002", ticket.getParkingLotId());
    }
}
