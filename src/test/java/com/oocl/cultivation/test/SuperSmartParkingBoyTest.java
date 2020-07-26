package com.oocl.cultivation.test;

import com.oocl.cultivation.entity.Car;
import com.oocl.cultivation.entity.ParkingLot;
import com.oocl.cultivation.entity.SuperMartParkingBoy;
import com.oocl.cultivation.entity.Ticket;
import com.oocl.cultivation.exception.NotEnoughPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyTest {

    @Test
    void should_parked_parking_lot_1_when_parking_car_given_parking_lot_1_parking_9_car_and_parking_lot_2_parking_no_car() throws NotEnoughPositionException {
        // given
        SuperMartParkingBoy superMartParkingBoy = new SuperMartParkingBoy();

        ParkingLot parkingLot1 = new ParkingLot("PARKINGLOT1");
        for (int i = 1; i < ParkingLot.CAPACITY; i++) {
            parkingLot1.parking(new Car(String.format("CAR%d", i)));
        }
        superMartParkingBoy.addParkingLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot("PARKINGLOT2");
        superMartParkingBoy.addParkingLot(parkingLot2);

        Car car = new Car("CAR12");

        // when
        Ticket ticket = superMartParkingBoy.parkingCar(car);

        // then
        assertEquals("PARKINGLOT1", ticket.getParkingLotId());
    }
}
