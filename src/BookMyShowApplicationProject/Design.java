package BookMyShowApplicationProject;

public class Design {
    public static void main(String[] args){
        /* BookMyShow
        Overview :-
        1) Align yourself with interviewer's understandings.
        2) What to design ? means an Entity relation diagram, or a command line application or a REST API.
        3) How to take input params ?
        4) Does data needs to persist ? means do we need to store it in database ?
        So this is an online ticket booking platform for movies that are available in theaters.
        ------------------------------------------------------------------------------------------------
        Requirement Gathering :-
        First of all create account and login, then select your city, it means it should support multiple
        cities and then running shows should be made available to the user, and then they should be able to
        make selection from available seats of the selected show and selected theater.
        Some of basic requirements are :-
        1) User should be able to book movie ticket.
        2) User should be able to select seats while booking.
        3) BookMyShow supports multiple cities, each city will have multiple theaters and list
        of shows that are running.
        4) BookMyShow only supports movie booking.
        5) Each theater will have multiple screens and each screen will have multiple seats.
        6) One screen can run one movie at a time.
        7) User can book ticket for a particular show.
        8) Show is particular movie running at a particular screen at a particular time and date.
        9) BookMyShow will list down all the moves running in a city.
        10) In one ticket, a user can select maximum 10 seats.
        11) No two users should be able to book a ticket for a same show.
        12) Price will be a function of Show + seatType +
        13) Only online payment is supported for payments, we will use 3rd party payment gateways.
        14) Partial payments are also supported, so if some payments are done partially from cash and
        with some offers or some coupon codes, then they can be tracked down and can be reverted in the
        similar form.
        15) For every movie, we will store its cast, languages, genre etc.
        16) Each movie supports multiple features like 2D, 3D, IMAX.

        Now there are some features supported by a movie and some features are supported by the screen like
        some screen cannot play 3D movies or some screen don't support IMAX but that movie is shot from
        an IMAX camera, so this will be a limitation while deciding for a specific movie in a specific screen
        then there will be an ambiguity for a show with only IMAX capability to be played on a normal screen
        so a show features will be properties that intersects with both screen and a movie.

        17) Seats can be different types.
        18) BMS is just an aggregator, it does not store any data related to any theater or any seat,
        and whenever any seat related information is required, it queries to the respective API  
        and makes a check for the availability and if there is, then send a response with confirmation,
        so source of data are the theater APIs.
        Users can also book ticket through native theater's website and other aggregator as well so an
        API can be accessed at a same time by different aggregator and User as well so handling concurrency
        becomes the job of these data providers theaters APIs, but at the same time BookMyShow will be handling
        concurrency at its own level as there can be multiple users trying to access the same seat or same
        show at a time and that is supposed to be handled by BookMyShow itself.
        -----------------------------------------------------------------------------------------------------
        Class diagram :-
        1) Visualization
        it can be done by Physical structure and User journey
        User journey :- each city will have multiple theaters and each theater will have multiple screens

        class City {            class Theater {         class Screen {
            id                      id                      id
            cityName                theaterName             name
            List<Theater>           List<Screens>           List<Seat>
        }                       }                           List<Feature>
                                                            OperationStatus
                                                        }
        Here if the name of SeatType is common across all the theaters, then using ENUM is a good choice
        but if tha type of choice is not fixed then a class is a better choice like it is easy to change the
        name for the type of the seats.
        enum Feature {          class Seat {            class SeatType {
            2D                      id                      SILVER,
            3D                      seatNumber              GOLD,
            4D                      SeatType                PLATINUM
            DOLBY-ATOMS         }                       }
            IMAX
        }
        Now here comes a small twist, first of all, do class seat requires status attribute ? yes it does
        as we need to check whether the seat is booked or available, but if we understand it closely, a seat
        is never blocked for whole day, it only blocked for a specific show and later that seat is released
        so if we think straight, status needs to be included in seat class but status itself is not only
        linked with seat, status is combination of show + seat that means we need to create some middle
        class which have purpose of storing seat status for a particular show. So status will not be the part
        of Seat class.

        class Show {            class ShowSeat {               class ShowSeatPrice {
            id                      Show show                       Show show
            movie                   Seat seat                       SeatType
            startTime               Status                          price
            endTime             }                               }
            Screen
            List<Feature>
        }
        In class Ticket, there are some attributes that are common, but the List<ShowSeat> is somewhat
        concerning here, it's way more simple as we book seats for a show, not the hall's seat.
        class Ticket {          class Payment {
            id                      id
            ticketNumber            amount
            ticketStatus            Mode
            User                    reference_no
            Show                    status
            amount              }
            List<ShowSeat>
        }
        * */
    }
}
