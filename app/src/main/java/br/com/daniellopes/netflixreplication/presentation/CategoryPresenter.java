package br.com.daniellopes.netflixreplication.presentation;

import java.util.ArrayList;
import java.util.List;

import br.com.daniellopes.netflixreplication.datasource.CategoryDataSource;
import br.com.daniellopes.netflixreplication.interfaces.CategoryCallback;
import br.com.daniellopes.netflixreplication.model.Category;
import br.com.daniellopes.netflixreplication.model.Movie;
import br.com.daniellopes.netflixreplication.model.CategoryItem;
import br.com.daniellopes.netflixreplication.ui.MainActivity;

public class CategoryPresenter implements CategoryCallback {

    private MainActivity view;
    private CategoryDataSource dataSource;

    public CategoryPresenter(MainActivity view, CategoryDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void requestAll() {
        this.view.showProgressbar();
        dataSource.find(this);
    }

    @Override
    public void onSuccess(CategoryItem categoryItem) {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryItem.getCategories()) {
            String title = category.getTitle();
            List<Movie> movies = category.getMovies();
            categories.add(new Category(category.getId(), title, movies));
        }
        this.view.showCategory(categories);
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
