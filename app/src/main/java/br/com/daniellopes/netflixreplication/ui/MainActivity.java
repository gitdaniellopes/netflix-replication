package br.com.daniellopes.netflixreplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.daniellopes.netflixreplication.R;
import br.com.daniellopes.netflixreplication.adapter.MainAdapter;
import br.com.daniellopes.netflixreplication.datasource.CategoryDataSource;
import br.com.daniellopes.netflixreplication.model.Category;
import br.com.daniellopes.netflixreplication.presentation.CategoryPresenter;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        List<Category> categories = new ArrayList<>();
        mainAdapter = new MainAdapter(this, categories);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));


        CategoryDataSource dataSource = new CategoryDataSource();
        CategoryPresenter presenter = new CategoryPresenter(this, dataSource);

        presenter.requestAll();

    }

    public void showCategory(List<Category> categories) {
        mainAdapter.addAll(categories);
        mainAdapter.notifyDataSetChanged();
    }

    public void showFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showProgressbar() {
        createdDialog();
        dialog.show();
    }

    public void hideProgressBar() {
        dialog.dismiss();
    }

    private void createdDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        dialog = dialogBuilder.create();
    }

    private void bind() {
        recyclerView = findViewById(R.id.recycle_view_main);
    }
}