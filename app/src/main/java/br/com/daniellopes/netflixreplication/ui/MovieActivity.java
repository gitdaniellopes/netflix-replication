package br.com.daniellopes.netflixreplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.daniellopes.netflixreplication.R;
import br.com.daniellopes.netflixreplication.adapter.MovieDetailAdapter;
import br.com.daniellopes.netflixreplication.datasource.retrofit.MovieDetailDataSource;
import br.com.daniellopes.netflixreplication.model.Movie;
import br.com.daniellopes.netflixreplication.model.MoviesSimilar;
import br.com.daniellopes.netflixreplication.presentation.MoviePresenter;

public class MovieActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";
    private Toolbar toolbar;
    private int id;
    private AlertDialog dialog;

    private TextView txtTitle;
    private TextView txtDesc;
    private TextView txtCast;
    private ImageView imageCover;
    private RecyclerView recyclerView;
    private MovieDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        bind();

        setSupportActionBar(toolbar);
        configToolbar();
        getMovieSent();

        List<MoviesSimilar> movies = new ArrayList<>();
        adapter = new MovieDetailAdapter(movies, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        MovieDetailDataSource dataSource = new MovieDetailDataSource();
        MoviePresenter presenter = new MoviePresenter(this, dataSource);

        presenter.getMovie(id);

    }

    private void getMovieSent() {
        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getInt(KEY_ID);
        }
    }

    private void configToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            getSupportActionBar().setTitle(null);
        }
    }

    public void showMovieDetail(Movie movie) {
        txtTitle.setText(movie.getTitle());
        txtDesc.setText(movie.getDesc());
        txtCast.setText(movie.getCast());
        Picasso.get().load(movie.getCoverUrl()).into(imageCover);
        List<MoviesSimilar> moviesSimilar = movie.getMoviesSimilar();
        showMoviesSimilar(moviesSimilar);

    }

    private void showMoviesSimilar(List<MoviesSimilar> moviesSimilar) {
        adapter.addMoviesSimilar(moviesSimilar);
        adapter.notifyDataSetChanged();
    }

    public void showFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showProgressbar() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        dialog = dialogBuilder.create();
        dialog.show();

    }

    public void hideProgressBar() {
        dialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bind() {
        imageCover = findViewById(R.id.image_view_movie_cover);
        recyclerView = findViewById(R.id.recycle_view_similar);
        txtTitle = findViewById(R.id.text_view_movie_title);
        txtDesc = findViewById(R.id.text_view_movie_desc);
        txtCast = findViewById(R.id.text_view_movie_cast);
        toolbar = findViewById(R.id.toolbar);
    }
}