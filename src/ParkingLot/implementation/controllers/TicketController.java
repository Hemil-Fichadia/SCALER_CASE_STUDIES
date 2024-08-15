package ParkingLot.implementation.controllers;

import ParkingLot.implementation.dtos.IssueTicketRequestDto;
import ParkingLot.implementation.dtos.IssueTicketResponseDto;
import ParkingLot.implementation.dtos.ResponseStatus;
import ParkingLot.implementation.exceptions.GateNotFoundException;
import ParkingLot.implementation.models.Ticket;
import ParkingLot.implementation.services.TicketService;

public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto requestDto){
        IssueTicketResponseDto responseDto = new IssueTicketResponseDto();
        try{
            Ticket ticket = ticketService.issueTicket(
                    requestDto.getGateId(),
                    requestDto.getVehicleNumber(),
                    requestDto.getVehicleOwnerName(),
                    requestDto.getVehicleType()
            );
        }
        catch (GateNotFoundException gateNotFoundException){
            gateNotFoundException.getMessage();
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return null;
    }
}
