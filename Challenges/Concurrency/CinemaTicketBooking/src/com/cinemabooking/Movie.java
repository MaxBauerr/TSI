package com.cinemabooking;

import java.util.Arrays;
import java.util.Random;

public class Movie {
    private final String title;
    private final boolean[] seats;
    private final Random random = new Random();

    public Movie(String title, int numberOfSeats) {
        this.title = title;
        this.seats = new boolean[numberOfSeats];
        Arrays.fill(this.seats, true);
    }

    public String getTitle() {
        return title;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public synchronized String bookSeat(int seatNumber) {
        if (!seats[seatNumber]) {
            return "Seat " + seatNumber + " for " + title + "\033[31m" + " IS NOT AVAILABLE" + "\033[0m";
        } else {
            try {
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            seats[seatNumber] = false;
            return "Ticket for seat " + seatNumber + " for " + title;
        }
    }

    public synchronized boolean areAllSeatsBooked() {
        for (boolean seat : seats) {
            if (seat) return false;
        }
        return true;
    }

    public void bookRandomInitialSeats(int maxSeatsToBook) {
        int seatsToBook = random.nextInt(maxSeatsToBook + 1);
        for (int i = 0; i < seatsToBook; i++) {
            int seat;
            do {
                seat = random.nextInt(seats.length);
            } while (!seats[seat]);
            seats[seat] = false;
        }
    }
}
