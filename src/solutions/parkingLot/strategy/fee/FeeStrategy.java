
package solutions.parkingLot.strategy.fee;

import solutions.parkingLot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}