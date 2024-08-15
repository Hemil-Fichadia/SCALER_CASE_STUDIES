package ParkingLot.implementation.factory;

import ParkingLot.implementation.models.SpotAssignmentStrategyType;
import ParkingLot.implementation.strategies.RandomSpotAssignmentStrategy;
import ParkingLot.implementation.strategies.SpotAssignmentStrategy;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategyForType(SpotAssignmentStrategyType spotAssignmentStrategyType){
//        if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)){
//            return new NearestSpotAssignmentStrategy();
//        }
//        else if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.CHEAPEST)){
//            return new CheapestSpotAssignmentStrategy();
//        }
        return new RandomSpotAssignmentStrategy();
    }
}
