package ParkingLot.implementation.services;

import ParkingLot.implementation.exceptions.GateNotFoundException;
import ParkingLot.implementation.factory.SpotAssignmentStrategyFactory;
import ParkingLot.implementation.models.*;
import ParkingLot.implementation.repositories.GateRepository;
import ParkingLot.implementation.repositories.ParkingLotRepository;
import ParkingLot.implementation.repositories.TicketRepository;
import ParkingLot.implementation.repositories.VehicleRepository;
import ParkingLot.implementation.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;
    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository){
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }
    public Ticket issueTicket(Long gateId,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              VehicleType vehicleType) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());
        //Get the gate object from the gateId
        /* Instead of just receiving a normal object, we are receiving Optional of an object so this helps
        us to avoid NullPointer exceptions, and it also supports some functions like isPresent() to check
        if there exist.
        * */
        Optional<Gate> optionalGate = gateRepository.findByGateId(gateId);
        if(optionalGate.isEmpty()){
            throw new GateNotFoundException("Invalid gate id");
        }
        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle savedVehicle = null;
        if(optionalVehicle.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);
            savedVehicle = vehicleRepository.save(vehicle);
        }
        else {
            savedVehicle = optionalVehicle.get();
        }
        ticket.setVehicle(savedVehicle);
        //Assign the spot
        /* Here we have a small challenge in front of us, as we want to fetch SpotAssignmentStrategyType and
        that is linked with ParkingLot class and that is not directly accessible, so we need to access
        it with something that is linked with it as we are not incorporating a database, but we are building
        this project with an assumption that all the models are translated to a database, so we can get
        ParkingLotId from gateId that extracted from database and gate to parkingLot cardinality is 1 : M
        so we can get that from gateId and from ParkingLotId we can get SpotAssignmentStrategyType. We are
        having database of all the models, and so we are able to fetch the corresponding details but make
        sure to think of these aspects while taking on some decision.
        */
        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateId(gateId);
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();
        //From factory get the SpotAssignmentStrategy and from it get the spot for vehicle
        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmentStrategyFactory.
                getSpotAssignmentStrategyForType(spotAssignmentStrategyType);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);
        ticket.setParkingSpot(parkingSpot);
        ticket.setNumber("TICKET_" + gateId + "_" + ticket.getEntryTime());

        return ticketRepository.save(ticket);
    }
}
