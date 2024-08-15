package ParkingLot.implementation;

import ParkingLot.implementation.controllers.TicketController;
import ParkingLot.implementation.dtos.IssueTicketRequestDto;
import ParkingLot.implementation.dtos.IssueTicketResponseDto;
import ParkingLot.implementation.models.VehicleType;
import ParkingLot.implementation.repositories.GateRepository;
import ParkingLot.implementation.repositories.ParkingLotRepository;
import ParkingLot.implementation.repositories.TicketRepository;
import ParkingLot.implementation.repositories.VehicleRepository;
import ParkingLot.implementation.services.TicketService;

public class client {
    public static void main(String[] args){
        /*
        */
        IssueTicketRequestDto requestDto = new IssueTicketRequestDto();
        requestDto.setGateId(123L);
        requestDto.setVehicleType(VehicleType.SUV);
        requestDto.setVehicleNumber("GJ01SU7942");
        requestDto.setVehicleOwnerName("Deepak");
        /* We cannot create this controller object as it have many inter-dependent objects like, controller
        is having a service class's object and service is having multiple repositories object so for
        creating object of TicketService we need object of repositories.
        Now it is next to impossible to manually create such objects, as we need to rectify the leaf
        object first and then backtracking to the main object and here comes SpringBoot as helping
        hand as we annotate some class as @Repository it creates an object of that class and manages it
        according to its responsibility.
        * */
        GateRepository gateRepository = new GateRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(ticketService);
        IssueTicketResponseDto responseDto = ticketController.issueTicket(requestDto);
    }
}
