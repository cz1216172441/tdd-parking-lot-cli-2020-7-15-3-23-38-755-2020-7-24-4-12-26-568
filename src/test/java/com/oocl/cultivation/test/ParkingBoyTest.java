package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingBoyTest {

    @Test
    void should_return_1_ticket_when_parking_car_given_1_car_and_1_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(1);
        Car car = new Car("CAR001");
        // when
        String ticket = parkingBoy.parkingCar(car);
        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_1_car_when_fetching_car_given_1_ticker_1_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(1);
        String ticker = "CAR001";
        // when
        Car car = parkingBoy.fetchingCar(ticker);
        // then
        Assertions.assertNotNull(car);
    }
}
