package br.com.daniellopes.netflixreplication.datasource.retrofit;

import br.com.daniellopes.netflixreplication.model.CategoryItem;
import br.com.daniellopes.netflixreplication.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoint {

    String BASE_URL = "https://tiagoaguiar.co/api/netflix/";

    @GET("home")
    Call<CategoryItem> getCategories();

    @GET("{id}")
    Call<Movie> getMovie(@Path("id") int id);

}
