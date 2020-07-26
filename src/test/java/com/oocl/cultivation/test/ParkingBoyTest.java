package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.*;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void should_return_ticket_with_parking_lot_2_when_parking_car_given_a_smart_parking_boy_and_a_car_and_2_parking_lots_and_parking_lot2_contain_more_empty_position() throws NotEnoughPositionException {
        // given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Car car = new Car("CARXX");
        ParkingLot parkingLot2 = new ParkingLot("PARKINGLOT002");
        smartParkingBoy.addParkingLot(parkingLot1);
        smartParkingBoy.addParkingLot(parkingLot2);
        for (int i = 1; i <= ParkingLot.CAPACITY - 1; i++) {
            parkingLot1.getCars().add(new Car(String.format("CAR%d", i)));
        }
        // when
        Ticket ticket = smartParkingBoy.parkingCar(car);
        // then
        Assertions.assertEquals("CARXX", ticket.getCarId());
        Assertions.assertEquals("PARKINGLOT002", ticket.getParkingLotId());
    }
}
