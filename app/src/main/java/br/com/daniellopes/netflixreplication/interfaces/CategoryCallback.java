package br.com.daniellopes.netflixreplication.interfaces;

import br.com.daniellopes.netflixreplication.model.CategoryItem;

public interface CategoryCallback {
    void onSuccess(CategoryItem categoryItem);

    void onError(String message);

    void onComplete();
}
