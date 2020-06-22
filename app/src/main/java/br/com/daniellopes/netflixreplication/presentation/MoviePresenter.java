package br.com.daniellopes.netflixreplication.presentation;

import br.com.daniellopes.netflixreplication.datasource.retrofit.MovieDetailDataSource;
import br.com.daniellopes.netflixreplication.interfaces.MovieDetailCallback;
import br.com.daniellopes.netflixreplication.model.Movie;
import br.com.daniellopes.netflixreplication.ui.MovieActivity;

public class MoviePresenter implements MovieDetailCallback {

    private MovieActivity view;
    private MovieDetailDataSource dataSource;

    public MoviePresenter(MovieActivity view, MovieDetailDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void getMovie(int id) {
        this.view.showProgressbar();
        dataSource.findMovie(this, id);
    }

    @Override
    public void onSuccess(Movie movie) {
        this.view.showMovieDetail(movie);
    }

    @Override
    public void onError(String message) {
        this.view.showFailure(message);
    }

    @Override
    public void onComplete() {
        this.view.hideProgressBar();
    }
}
