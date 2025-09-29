package strategy.parking;

import java.util.Optional;
import java.util.List;

import entities.ParkingFloor;
import entities.ParkingSpot;
import vehicle.Vehicle;

public interface ParkingStartegy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
