package br.com.daniellopes.netflixreplication.datasource.retrofit;

import br.com.daniellopes.netflixreplication.interfaces.MovieDetailCallback;
import br.com.daniellopes.netflixreplication.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailDataSource {

    public void findMovie(MovieDetailCallback callback, int id) {
        HttpClient.retrofit().create(EndPoint.class)
                .getMovie(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                callback.onComplete();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onError(t.getMessage());
                callback.onComplete();
            }
        });
    }
}
