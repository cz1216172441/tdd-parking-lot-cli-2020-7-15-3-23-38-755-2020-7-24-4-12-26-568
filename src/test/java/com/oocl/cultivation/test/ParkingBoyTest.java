package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingBoy;
import com.oocl.cultivation.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void should_return_1_car_when_fetching_car_given_1_ticker_1_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = new Ticket("CAR001");
        // when
        Car car = parkingBoy.fetchingCar(ticket);
        // then
        Assertions.assertEquals("CAR001", car.getId());
    }

}
