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
import br.com.daniellopes.netflixreplication.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movies;
    private OnItemClickListener listener;

    interface OnItemClickListener {
        void onClick(int position);
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.
                from(context)
                .inflate(R.layout.item_list_movie, parent, false);
        return new MovieViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie, listener);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.image_view_cover);
        }

        public void bind(Movie movie, OnItemClickListener listener) {
            Picasso.get().load(movie.getCoverUrl()).into(imageViewMovie);
            itemView.setOnClickListener(view -> listener.onClick(getAdapterPosition()));
        }
    }
}
