package br.com.daniellopes.netflixreplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.daniellopes.netflixreplication.R;
import br.com.daniellopes.netflixreplication.model.Category;
import br.com.daniellopes.netflixreplication.ui.MovieActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categories;

    public MainAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater
                .from(context)
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category, context);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addAll(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCategory;
        private RecyclerView recyclerViewMovie;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategory = itemView.findViewById(R.id.text_view_category);
            recyclerViewMovie = itemView.findViewById(R.id.recycle_view_movie);
        }

        public void bind(Category category, final Context context) {
            txtCategory.setText(category.getTitle());
            MovieAdapter movieAdapter = new MovieAdapter(context, category.getMovies());
            recyclerViewMovie.setAdapter(movieAdapter);
            recyclerViewMovie.setLayoutManager(new LinearLayoutManager(context,
                    RecyclerView.HORIZONTAL, false));

            movieAdapter.setOnItemClickListener(position -> {
                if (category.getMovies().get(position).getId() <= 3) {
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("id", category.getMovies().get(position).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
