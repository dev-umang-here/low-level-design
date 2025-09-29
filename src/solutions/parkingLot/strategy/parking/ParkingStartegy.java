package solutions.parkingLot.strategy.parking;

import java.util.Optional;
import java.util.List;

import solutions.parkingLot.entities.ParkingFloor;
import solutions.parkingLot.entities.ParkingSpot;
import solutions.parkingLot.vehicle.Vehicle;

public interface ParkingStartegy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
