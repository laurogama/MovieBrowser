package com.android.example.mypopularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.mypopularmovies.controller.ImdbController;
import com.android.example.mypopularmovies.models.ApiResponse;
import com.android.example.mypopularmovies.models.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ApiResponse>, MovieAdapter.ListItemClickListener {

    private RecyclerView recyclerView;
    private TextView mErrorTextView;
    private ProgressBar mProgressBar;
    private Toast mToast;
    private ImdbController.SortType mSortType = ImdbController.SortType.POPULAR;
    private ImdbController mImdbController = new ImdbController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mErrorTextView = findViewById(R.id.tv_error_loading_movies);
        mProgressBar = findViewById(R.id.pb_load_movies);
        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        mProgressBar.setVisibility(View.VISIBLE);
        mImdbController.loadMovies(mSortType, this);
    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        if (response.isSuccessful()) {
            ApiResponse apiResponse = response.body();
            updateMovies(apiResponse.getResults());
        } else {
            System.out.println(response.headers().toString());
            System.out.println(response.code());
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {

        showErrorRetrievingMovies();
        t.printStackTrace();
    }

    private void showErrorRetrievingMovies() {
        mProgressBar.setVisibility(View.GONE);
        mErrorTextView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort_popular:
                Toast.makeText(this, "Sort by Most Popular", Toast.LENGTH_LONG).show();
                mSortType = ImdbController.SortType.POPULAR;
                mImdbController.loadMovies(mSortType, this);
                break;
            case R.id.action_sort_rated:
                Toast.makeText(this, "Sort by Most Rated", Toast.LENGTH_LONG).show();
                mSortType = ImdbController.SortType.RATED;
                mImdbController.loadMovies(mSortType, this);
                break;
            default:
                super.onOptionsItemSelected(item);
                break;

        }
        return true;

    }

    @Override
    public void onListItemClick(MovieModel clickedMovie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, clickedMovie);
        startActivity(intent);
    }

    private void updateMovies(List<MovieModel> movies) {
        mProgressBar.setVisibility(View.GONE);
        mErrorTextView.setVisibility(View.GONE);
        recyclerView.setAdapter(new MovieAdapter(movies, this));
    }
}
