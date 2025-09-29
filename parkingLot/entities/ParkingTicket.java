package entities;

import java.util.Date;
import java.util.UUID;

import vehicle.Vehicle;

public class ParkingTicket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final long entryTimestamp;
    private long exitTimestamp;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTimestamp = new Date().getTime();
    }

    public void setExitTimestamp() {
        this.exitTimestamp = new Date().getTime();
    }

    public long getEntryTimestamp() {
        return entryTimestamp;
    }

    public long getExitTimestamp() {
        return exitTimestamp;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
