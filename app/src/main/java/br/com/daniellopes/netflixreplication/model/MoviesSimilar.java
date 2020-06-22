package br.com.daniellopes.netflixreplication.model;

import com.google.gson.annotations.SerializedName;

public class MoviesSimilar {

    private int id;
    @SerializedName("cover_url")
    private String coverUrl;

    public MoviesSimilar(int id, String coverUrl) {
        this.id = id;
        this.coverUrl = coverUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

}
