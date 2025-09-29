package solutions.parkingLot.strategy.parking;

import java.util.*;

import solutions.parkingLot.entities.ParkingFloor;
import solutions.parkingLot.entities.ParkingSpot;
import solutions.parkingLot.vehicle.Vehicle;

public class NearestFirstStrategy implements ParkingStartegy {

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }

}