package com.cinemabooking;

import java.util.HashMap;
import java.util.Map;

public class Cinema {
    private final String name;
    private final Map<String, Movie> movies;

    public Cinema(String name) {
        this.name = name;
        this.movies = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getTitle(), movie);
    }

    public Movie getMovie(String title) {
        return movies.get(title);
    }

    public Map<String, Movie> getMovies() {
        return movies;
    }
}
