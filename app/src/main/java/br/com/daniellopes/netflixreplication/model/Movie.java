package br.com.daniellopes.netflixreplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    private final int id;
    @SerializedName("cover_url")
    private final String coverUrl;
    private final String title;
    private final String desc;
    private final String cast;

    @SerializedName("movie")
    private final List<MoviesSimilar> moviesSimilar;

    public Movie(int id, String coverUrl, String title, String desc, String cast, List<MoviesSimilar> moviesSimilar) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.title = title;
        this.desc = desc;
        this.cast = cast;
        this.moviesSimilar = moviesSimilar;
    }


    public int getId() {
        return id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCast() {
        return cast;
    }

    public List<MoviesSimilar> getMoviesSimilar() {
        return moviesSimilar;
    }


}
