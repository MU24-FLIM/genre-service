package com.example.genre;

public class Movie {

    private Long id;

    private Long genreId;

    private String title;

    private String director;

    private int released;

    public Movie() {
    }

    public Movie(Long id, Long genreId, String title, String director, int released) {
        this.id = id;
        this.genreId = genreId;
        this.title = title;
        this.director = director;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }
}
