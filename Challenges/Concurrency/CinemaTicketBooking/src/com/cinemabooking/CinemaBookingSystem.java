package com.cinemabooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CinemaBookingSystem {
    public static void main(String[] args) {
        Random random = new Random();
        String[] cinemaNames = {"Birmingham", "Coventry", "Wolverhampton", "West Bromwich", "Walsall"};
        String[] movieTitles = {"Oppenheimer 2", "Barbie 2", "Fast and Furious XX", "Interstellar 2", "Maze Runner 3"};
        final int numberOfSeats = random.nextInt(5);
        final int numberOfCustomers = random.nextInt(100);
        List<Cinema> cinemas = new ArrayList<>();
        for (String cinemaName : cinemaNames) {
            Cinema cinema = new Cinema(cinemaName);
            for (String title : movieTitles) {
                Movie movie = new Movie(title, numberOfSeats);
                movie.bookRandomInitialSeats(2);
                cinema.addMovie(movie);
            }
            cinemas.add(cinema);
        }
        for (int i = 1; i <= numberOfCustomers; i++) {
            Cinema selectedCinema = cinemas.get(random.nextInt(cinemas.size()));
            List<Movie> movies = new ArrayList<>(selectedCinema.getMovies().values());
            Movie selectedMovie = movies.get(random.nextInt(movies.size()));
            new Customer(selectedCinema, selectedMovie, i).start();
        }
    }
}
