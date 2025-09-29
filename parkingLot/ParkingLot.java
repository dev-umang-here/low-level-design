import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import entities.ParkingTicket;
import strategy.fee.FeeStrategy;
import strategy.fee.FlatRateFeeStrategy;
import strategy.parking.NearestFirstStrategy;
import strategy.parking.ParkingStartegy;
import vehicle.Vehicle;
import entities.ParkingFloor;
import entities.ParkingSpot;

public class ParkingLot {

    private static ParkingLot instance;
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets = new ConcurrentHashMap<>();
    private FeeStrategy feeStrategy;
    private ParkingStartegy parkingStartegy;

    private ParkingLot() {
        // private constructor
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingStartegy = new NearestFirstStrategy();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStartegy parkingStartegy) {
        this.parkingStartegy = parkingStartegy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStartegy.findSpot(floors, vehicle);

        if (availableSpot.isPresent()) {
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle, spot);
            activeTickets.put(vehicle.getLicenseNumber(), ticket);
            return Optional.of(ticket);
        }
        System.out.println("No Available spot for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);
        if (ticket == null) {
            System.out.print("Ticket not found for " + licenseNumber);
            return Optional.empty();
        }
        ticket.setExitTimestamp();
        ticket.getSpot().unparkVehicle();
        activeTickets.remove(ticket.getTicketId());
        Double parkingFee = feeStrategy.calculateFee(ticket);
        return Optional.of(parkingFee);
    }
}
