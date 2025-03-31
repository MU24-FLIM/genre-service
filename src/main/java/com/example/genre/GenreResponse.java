package com.example.genre;

import java.util.List;


//Kopplar ihop min Movie och Genre
public class GenreResponse {

    private Genre genre;

    private List<Movie> movies;

    public GenreResponse(Genre genre, List<Movie> movies) {
        this.genre = genre;
        this.movies = movies;
    }

    public GenreResponse() {
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
