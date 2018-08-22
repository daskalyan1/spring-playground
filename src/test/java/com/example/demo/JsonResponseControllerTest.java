package com.example.demo;

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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONResponseController.class)
public class JsonResponseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testFlight() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/json/flights/flight");
        request.accept(MediaType.APPLICATION_JSON_UTF8);
        request.contentType(MediaType.APPLICATION_JSON_UTF8);
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is ("Kalyan")))
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
            .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is ("Kalyan")))
            .andExpect(jsonPath("$[0].Tickets[0].Price", is(100)))
            .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is ("Kalyan1")))
            .andExpect(jsonPath("$[1].Tickets[0].Price", is(200)));
    }
}
