package br.com.daniellopes.netflixreplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    private final int id;
    private final String title;

    @SerializedName("movie")
    private final List<Movie> movies;

    public Category(int id, String title, List<Movie> movies) {
        this.id = id;
        this.title = title;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
