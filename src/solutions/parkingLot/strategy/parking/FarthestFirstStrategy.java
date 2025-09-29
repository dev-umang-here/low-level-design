package solutions.parkingLot.strategy.parking;

import solutions.parkingLot.entities.ParkingFloor;
import solutions.parkingLot.entities.ParkingSpot;
import solutions.parkingLot.vehicle.Vehicle;

import java.util.*;

public class FarthestFirstStrategy implements ParkingStartegy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        // reverse the floors and search from the top floor

        List<ParkingFloor> reversedFloors = new ArrayList<>();
        Collections.reverse(reversedFloors);

        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}