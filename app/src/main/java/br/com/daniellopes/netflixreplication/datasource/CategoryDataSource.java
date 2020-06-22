package br.com.daniellopes.netflixreplication.datasource;

import br.com.daniellopes.netflixreplication.datasource.retrofit.EndPoint;
import br.com.daniellopes.netflixreplication.datasource.retrofit.HttpClient;
import br.com.daniellopes.netflixreplication.interfaces.CategoryCallback;
import br.com.daniellopes.netflixreplication.model.CategoryItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDataSource {

    public void find(CategoryCallback callBack) {
        HttpClient.retrofit().create(EndPoint.class)
                .getCategories().enqueue(new Callback<CategoryItem>() {
            @Override
            public void onResponse(Call<CategoryItem> call, Response<CategoryItem> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                }
                callBack.onComplete();
            }

            @Override
            public void onFailure(Call<CategoryItem> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
}
