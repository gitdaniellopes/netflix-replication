package br.com.daniellopes.netflixreplication.interfaces;

import br.com.daniellopes.netflixreplication.model.Movie;

public interface MovieDetailCallback {

    void onSuccess(Movie movie);

    void onError(String message);

    void onComplete();
}
