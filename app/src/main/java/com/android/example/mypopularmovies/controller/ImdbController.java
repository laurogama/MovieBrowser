package com.android.example.mypopularmovies.controller;

import com.android.example.mypopularmovies.models.ImdbApi;
import com.android.example.mypopularmovies.models.MovieResponse;
import com.android.example.mypopularmovies.models.ReviewResponse;
import com.android.example.mypopularmovies.models.TrailerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.example.mypopularmovies.models.ImdbApi.API_KEY;

public class ImdbController {
    private static final String IMDB_BASE_URL = "http://api.themoviedb.org/";


    public void loadMovies(SortType type, Callback<MovieResponse> callback) {
        Call<MovieResponse> call = type == SortType.POPULAR ? load().loadPopularMovies(API_KEY)
                : load().loadBestRatedMovies(API_KEY);
        System.out.println(call.request().url());
        call.enqueue(callback);
    }

    public void loadReviews(Integer id, Callback<ReviewResponse> callback) {
        Call<ReviewResponse> call = load().loadReviews(id, API_KEY);
        System.out.println(call.request().url());
        call.enqueue(callback);
    }

    public void loadTrailers(Integer id, Callback<TrailerResponse> callback) {
        Call<TrailerResponse> call = load().loadTrailers(id, API_KEY);
        System.out.println(call.request().url());
        call.enqueue(callback);
    }

    private ImdbApi load() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ImdbApi.class);
    }

    public enum SortType {
        POPULAR, RATED
    }
}
