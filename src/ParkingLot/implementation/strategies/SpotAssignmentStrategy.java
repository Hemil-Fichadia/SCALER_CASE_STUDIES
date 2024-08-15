package ParkingLot.implementation.strategies;

import ParkingLot.implementation.models.Gate;
import ParkingLot.implementation.models.ParkingSpot;
import ParkingLot.implementation.models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
