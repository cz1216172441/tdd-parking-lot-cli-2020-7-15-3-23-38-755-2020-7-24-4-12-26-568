package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingBoy;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.Ticket;
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
    void should_return_1_car_when_fetching_car_given_1_ticker_and_1_parking_boy_and_1_parking_lot_with_1_car() {
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
    void should_return_1_correct_car_when_fetching_car_given_1_ticket_and_1_packing_boy_and_1_parking_lot_with_2_car() {
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
    void should_return_no_car_when_fetching_car_given_1_wrong_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() {
        // given
        Ticket ticket = new Ticket("CAR001");
        parkingLot.getCars().add(new Car("CAR002"));
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_car_given_no_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() {
        // given
        parkingLot.getCars().add(new Car("CAR002"));
        // when
        Car car = parkingBoy.fetchingCar(null);
        // then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_car_given_1_used_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() {
        // given
        Ticket ticket = new Ticket("CAR001");
        parkingLot.getCars().add(new Car("CAR001"));
        // when
        parkingBoy.fetchingCar(ticket);
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertNull(car);
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
}
