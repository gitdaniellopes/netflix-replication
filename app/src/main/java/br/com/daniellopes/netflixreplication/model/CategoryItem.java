package br.com.daniellopes.netflixreplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryItem {

    @SerializedName("category")
    private final List<Category> categories;

    public CategoryItem(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

}
