package ParkingLot.implementation.repositories;

import ParkingLot.implementation.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    private Map<Long, Gate> gateMap = new HashMap<>();
    /* Here we have created an HashMap that will act like a pseudo database which stores the key of the
    data so this can act like a database with some data and a primary key.
    * */
    public Optional<Gate> findByGateId(Long gateId){
        if(gateMap.containsKey(gateId)){
            return Optional.of(gateMap.get(gateId));
        }
        return Optional.empty();
    }
}
