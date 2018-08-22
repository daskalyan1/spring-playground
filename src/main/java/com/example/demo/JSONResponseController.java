package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JSONResponseController {

    @GetMapping("/flights/flight")
    public Flight getFlight() {

        Flight flight = new Flight();
        flight.setDeparts(LocalDateTime.of(2018, 9, 12, 12, 30));

        Flight.Ticket ticket = new Flight.Ticket();
        Flight.Ticket.Person passenger = new Flight.Ticket.Person();
        passenger.setFirstName("Kalyan");
        passenger.setLastName("Das");
        ticket.setPassenger(passenger);
        ticket.setPrice(100);

        flight.setTickets(Arrays.asList(ticket));

        return flight;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {

        Flight flight1 = new Flight();
        flight1.setDeparts(LocalDateTime.of(2018, 10, 12, 14, 30));

        Flight.Ticket ticket1 = new Flight.Ticket();
        Flight.Ticket.Person passenger = new Flight.Ticket.Person();

        passenger.setFirstName("Kalyan");
        passenger.setLastName("Das");
        ticket1.setPassenger(passenger);
        ticket1.setPrice(100);

        Flight flight2 = new Flight();
        flight2.setDeparts(LocalDateTime.of(2018, 11, 16, 18, 30));

        Flight.Ticket ticket2 = new Flight.Ticket();
        Flight.Ticket.Person passenger2 = new Flight.Ticket.Person();
        passenger2.setFirstName("Kalyan1");
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(200);

        flight1.setTickets(Arrays.asList(ticket1));
        flight2.setTickets(Arrays.asList(ticket2));

        return Arrays.asList(flight1, flight2);
    }
}
