package solutions.parkingLot.entities;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import solutions.parkingLot.vehicle.Vehicle;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ConcurrentHashMap<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getSpotId(), spot);
    }

    public void removeSpot(String spotId) {
        spots.remove(spotId);
    }

    // Intially this was giving the Error
    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
        return spots.values().stream()
                .filter(spot -> !spot.isOccupied() && spot.canFitVehicle(vehicle))
                .sorted(Comparator.comparing(ParkingSpot::getSpotSize))
                .findFirst();
    }

    public void displayAvailability() {
        System.out.println("Parking Floor " + floorNumber + " Availability:");
        spots.values().forEach(
                spot -> System.out.println(spot.getSpotId() + " - " + (spot.isAvailable() ? "Available" : "Occupied")));
    }

}
