package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONResponseController.class)
public class JsonResponseControllerTest {

    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testFlight() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/json/flights/flight");
        request.accept(MediaType.APPLICATION_JSON_UTF8);
        request.contentType(MediaType.APPLICATION_JSON_UTF8);
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Kalyan")))
            .andExpect(jsonPath("$.Tickets[0].Price", is(100)));
    }

    @Test
    public void testFlights() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/json/flights");
        request.accept(MediaType.APPLICATION_JSON_UTF8);
        request.contentType(MediaType.APPLICATION_JSON_UTF8);
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Kalyan")))
            .andExpect(jsonPath("$[0].Tickets[0].Price", is(100)))
            .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Kalyan1")))
            .andExpect(jsonPath("$[1].Tickets[0].Price", is(200)));
    }

    @Test
    public void testCalculateTicketTotal() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post("/json/flights/tickets/total")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                "  \"Tickets\": [{\"Passenger\": {\"FirstName\": \"Some name\", \"lastName\": \"Some other name\"}, \"Price\": 200}, {\"Passenger\": {\"FirstName\": \"Name B\", \"LastName\": \"Name C\"}, \"Price\": 150}]\n" +
                "}");

        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("350"));
    }

    @Test
    public void testCalculateTicketTotalUsingObject() throws Exception {

        Flight flight = new Flight();

        Flight.Ticket ticket1 = new Flight.Ticket();
        Flight.Ticket.Person passenger1 = new Flight.Ticket.Person();
        passenger1.setFirstName("Kalyan");
        passenger1.setLastName("Das");
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(100);

        Flight.Ticket ticket2 = new Flight.Ticket();
        Flight.Ticket.Person passenger2 = new Flight.Ticket.Person();
        passenger2.setFirstName("Kalyan");
        passenger2.setLastName("Das");
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(300);

        flight.setTickets(Arrays.asList(ticket1, ticket2));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post("/json/flights/tickets/total")
            .contentType(MediaType.APPLICATION_JSON)
            .content( mapper.writeValueAsString(flight));

        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("400"));
    }

    @Test
    public void testCalculateTicketTotalUsingFileFixture() throws Exception {

        URL url = this.getClass().getResource("/data.json");
        String json = new String(Files.readAllBytes(Paths.get(url.getFile())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post( "/json/flights/tickets/total")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json);

        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("950"));
    }

}
