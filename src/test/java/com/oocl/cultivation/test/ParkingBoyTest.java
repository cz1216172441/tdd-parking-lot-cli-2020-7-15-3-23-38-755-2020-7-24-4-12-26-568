package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingBoy;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoyTest {

    @Test
    void should_return_1_ticket_when_parking_car_given_1_car_and_1_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car("CAR001");
        // when
        Ticket ticket = parkingBoy.parkingCar(car);
        // then
        Assertions.assertEquals("CAR001", ticket.getNumber());
    }

    @Test
    void should_return_1_car_when_fetching_car_given_1_ticker_and_1_parking_boy_and_1_parking_lot_with_1_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = new Ticket("CAR001");
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("CAR001"));
        ParkingLot parkingLot = new ParkingLot(cars);
        parkingBoy.setParkingLot(parkingLot);
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", car.getId());
    }

    @Test
    void should_return_2_ticket_when_parking_car_given_2_car_and_1_packing_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
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
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = new Ticket("CAR001");
        Car car001 = new Car("CAR001");
        Car car002 = new Car("CAR002");
        List<Car> cars = new ArrayList<>();
        cars.add(car001);
        cars.add(car002);
        ParkingLot parkingLot = new ParkingLot(cars);
        parkingBoy.setParkingLot(parkingLot);
        // when
        Car correctCar = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", correctCar.getId());
    }

    @Test
    void should_return_no_car_when_fetching_car_given_1_wrong_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = new Ticket("CAR001");
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("CAR002"));
        ParkingLot parkingLot = new ParkingLot(cars);
        parkingBoy.setParkingLot(parkingLot);
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_car_given_no_ticket_and_1_parking_boy_and_1_parking_log_with_1_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("CAR002"));
        ParkingLot parkingLot = new ParkingLot(cars);
        parkingBoy.setParkingLot(parkingLot);
        // when
        Car car = parkingBoy.fetchingCar(null);
        // then
        Assertions.assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetching_car_given_1_used_ticket_and_1_parking_boy_and_1_parking_lot_with_1_car() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = new Ticket("CAR001");
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("CAR001"));
        ParkingLot parkingLot = new ParkingLot(cars);
        parkingBoy.setParkingLot(parkingLot);
        // when
        parkingBoy.fetchingCar(ticket);
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertNull(car);
    }
}
