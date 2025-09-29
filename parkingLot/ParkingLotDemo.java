
import entities.ParkingFloor;
import entities.ParkingSpot;
import entities.ParkingTicket;
import strategy.fee.VehicleBasedFeeStrategy;
import strategy.parking.NearestFirstStrategy;
import vehicle.*;

import java.util.*;

public class ParkingLotDemo {

    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        ParkingFloor floor1 = new ParkingFloor(1);

        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot((new ParkingSpot("F1-M1", VehicleSize.MEDIUM)));
        floor1.addSpot((new ParkingSpot("F1-L1", VehicleSize.LARGE)));
        ParkingFloor floor2 = new ParkingFloor(2);

        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));
        floor2.addSpot((new ParkingSpot("F2-M2", VehicleSize.MEDIUM)));

        parkingLot.addFloor((floor1));
        parkingLot.addFloor(floor2);
        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());
        parkingLot.setParkingStrategy(new NearestFirstStrategy());

        System.out.println("\n --- Vehicle Entries------");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("B-123");
        Vehicle car = new Car("C-456");
        Vehicle truck = new Truck("T-789");

        Optional<ParkingTicket> bikeTicketOpt = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicketOpt = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicketOpt = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availabilty after Parking----");

        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle car2 = new Car("C-999");
        Optional<ParkingTicket> car2TicketOpt = parkingLot.parkVehicle(car2);

        Vehicle bike2 = new Bike("B-000");
        Optional<ParkingTicket> bike2TicketOpt = parkingLot.parkVehicle(bike2);

        if (carTicketOpt.isPresent()) {
            Optional<Double> feeOpt = parkingLot.unparkVehicle(car.getLicenseNumber());
            feeOpt.ifPresent(fee -> System.out.printf("Car C-456 unparked. Fee: $%.2f\n", fee));
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

    }
}