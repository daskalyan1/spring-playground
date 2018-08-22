package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import sun.security.krb5.internal.Ticket;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Flight {

    @JsonProperty("Departs")
    private LocalDateTime departs;
    private List<Ticket> tickets;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    public LocalDateTime getDeparts(){
        return departs;
    }
    public void setDeparts(LocalDateTime departsOn){
        this.departs = departsOn;
    }
    @JsonProperty("Tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }
    @JsonProperty("Tickets")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    static class Ticket {
        @JsonProperty("Price")
        private int price;
        @JsonProperty("Passenger")
        private Person passenger;

        public int getPrice(){
            return price;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        public Person getPassenger(){
            return passenger;
        }
        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        static class Person {
            @JsonProperty("FirstName")
            private String firstName;
            @JsonProperty("LastName")
            private String lastName;

            public String getFirstName() {
                return firstName;
            }
            public void setFirstName(String firstName){
                this.firstName = firstName;
            }
            public String getLastName() {
                return lastName;
            }
            public void setLastName(String lastName) {
                this.lastName = lastName;
            }
        }
    }

}
