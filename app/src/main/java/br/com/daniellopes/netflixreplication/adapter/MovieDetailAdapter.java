package br.com.daniellopes.netflixreplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.daniellopes.netflixreplication.R;
import br.com.daniellopes.netflixreplication.model.MoviesSimilar;

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailViewHolder> {

    private List<MoviesSimilar> movies;
    private Context context;

    public MovieDetailAdapter(List<MoviesSimilar> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public void addMoviesSimilar(List<MoviesSimilar> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    @NonNull
    @Override
    public MovieDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.from(context)
                .inflate(R.layout.item_movie_similar, parent, false);
        return new MovieDetailViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailViewHolder holder, int position) {
        MoviesSimilar movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieDetailViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewCover;

        public MovieDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCover = itemView.findViewById(R.id.image_view_cover_similar);
        }

        public void bind(MoviesSimilar movie) {
            Picasso.get().load(movie.getCoverUrl()).into(imageViewCover);
        }
    }
}
