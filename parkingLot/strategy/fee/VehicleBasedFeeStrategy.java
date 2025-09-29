package strategy.fee;

import java.util.Map;

import entities.ParkingTicket;
import vehicle.*;

public class VehicleBasedFeeStrategy implements FeeStrategy {

    private static final Map<vehicle.VehicleSize, Double> HOURLY_RATES = Map.of(
            VehicleSize.SMALL, 10.0,
            VehicleSize.MEDIUM, 20.0,
            VehicleSize.LARGE, 30.0);

    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        long duration = parkingTicket.getEntryTimestamp() - parkingTicket.getExitTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * HOURLY_RATES.get(parkingTicket.getVehicle().getSize());
    }
}
