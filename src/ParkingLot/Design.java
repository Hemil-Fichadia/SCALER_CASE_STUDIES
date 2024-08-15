package ParkingLot;

public class Design {
    public static void main(String[] args){
        /* Design Parking lot system
        Now onwards we are not just learning ParkingLot system, we are exploring all the aspects
        of software development and how to think in terms of a system that we are going to build,
        so try to think in all dimensions.
        ---------------------------------------------------------------------------------------------------
        Overview of the system :-
        We have to design a system, that manages multi-level parking building and also manages the people
        working there along with keep the track of the spots that are free or occupied and also keeps
        track of car and user that used a spot there.
        Do we need to build a full-fledged web-api system where people can book it online ? or a command line
        application, so here we are building command line application, that don't really need to store data
        permanently but, it should be managed till system is awake, so this is the case of In memory database.

        ------------------------------------------------------------------------------------------------------
        Requirement gathering :-
        Here we are expected to come up with at least 5 to 7 core functionalities that can act like o to 1
        1) Parking lot building will have multiple floors.
        2) Different parking spots for different types of vehicles.
        3) A token is generated at the time of entry
        4) Payment should be made at the time of exit
        5) A parking spot is assigned at the time of entry.
        6) To discourage longer duration of parking, variable charge is taken otherwise it is charged
        according to hourly basis, so a variable parking charge algorithm is needed, so there are multiple
        ways to charge for a parking so Strategy design pattern can be used.
        7) Payment can be done either in cash or online.
        8) Online payments will be handled by 3rd party payment gateway.

        Other way is by taking a user journey, like what will a user interact with, while entering the gate
        they will receive an entry token and will continue for the activity they came for and at the exit
        gate, they need to pay according to the time they acquired the parking spot.

        9) Multiple entry / exit gates can be there.
        10) Entry and exit gates are going to be separate.
        11) How to assign a parking spot and on what priority basis, like if a parking spot is on the top level
        is charged most and the one that is near to the entrance of the theme park or a theater is charged
        accordingly.
        12) Only a spot for the exact type of the vehicle can be assigned.
        ------------------------------------------------------------------------------------------------------
        Class diagram :-
        The class diagram can only be accomplished if there is some visualization about it and,
        visualization can be made with these two ways :-
        1) Physical structure
        2) User Journey

        class ParkingLot {              class ParkingFloor {
            id                              id
            List<Gate>                      FloorNumber
            List<ParkingFloor>              List<ParkingSpot>
            status                      }
            SpotAssignmentAlgo
            ChargeCalculationAlgo
            List<SupportedVehicle>
        }

        class EntryGate {               class ExitGate {                class Gate {
            id                              id                              id
            gateNumber                      gateNumber --------------->     gateNumber
            operator                        operator                        operator
            status                          status                          status
        }                               }                                   GateType
                                                                        }
        The above two classes of Gates have exactly same class attributes so they both are combined as one
        and named under one class Gate which have all the attributes of Gate and also a new attribute to
        identify Gate type GateType.

        class ParkingSpot {
            id
            number
            status
            VehicleType
        }
        Here we are not storing information of floor in ParkingSpot as it is rarely required operation,
        and we can simply iterate through floor list and get the information of a ParkingSpot. So store the
        information of an attribute of other class only if the information is going to be accessed
        frequently.

        class Vehicle {         class Operator {            class Ticket {
            id                      id                          id
            number                  EmployeeNumber              Number
            VehicleType             age                         EntryTime
            owner               }                               ParkingSpot
        }                                                       Vehicle vehicle
                                                                status
                                                                Gate
                                                                Operator
                                                            }
        class Bill {                    class Vehicle {             class UserDetail {
            id                              id                          id
            number                          vehiclenumber               name
            ExitTime                        VehicleType                 PhoneNumber
            Vehicle vehicle             }                               email
            amount                                                      Gender
            Ticket                                                  }
            Gate
            Operator
            List<Payment>
        }
        These are partial payments that's why list of payment objects are stored, so if someone paid
        200 in cash, and 300 online so this will maintain two different objects one of 200 Rs and another
        of 300 Rs online, so if there is any refund process expected in advanced, then the mode of transaction
        becomes crucial to revert the amount of payment, this is not so usual with parking lots, but just
        in case if required.

        class Payment {
            id
            amount
            time
            ModeOfTransaction
            PaymentStatus
            ReferenceId
        }
        * */
    }
}
