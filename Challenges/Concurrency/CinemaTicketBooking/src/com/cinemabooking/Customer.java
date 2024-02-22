package com.cinemabooking;

import java.util.Random;

public class Customer extends Thread {
    private final Cinema cinema;
    private final Movie movie;
    private final Random random = new Random();
    private final int id;
    private boolean hasBookedTicket;

    public Customer(Cinema cinema, Movie movie, int id) {
        this.cinema = cinema;
        this.movie = movie;
        this.id = id;
        this.hasBookedTicket = false;
    }

    @Override
    public void run() {
        while (!hasBookedTicket) {
            int requestedSeat = random.nextInt(movie.getSeats().length);
            String ticket = movie.bookSeat(requestedSeat);

            if (ticket.contains("Ticket")) {
                System.out.println("\nCustomer " + this.id + " at " + cinema.getName() + ": " + ticket);
                hasBookedTicket = true;
            } else {
                System.out.println("\nCustomer " + this.id + " at " + cinema.getName() + ": " + ticket);
                if (movie.areAllSeatsBooked()) {
                    System.out.println("\nCustomer " + this.id + " at " + cinema.getName() + ": " + movie.getTitle() + " is fully booked.");
                    break;
                }
            }
            if (!hasBookedTicket && movie.areAllSeatsBooked()) {
                System.out.println("\nCustomer " + this.id + " at " + cinema.getName() + ": Unable to book a ticket as all seats are booked.");
                break;
            }
        }
    }
}
