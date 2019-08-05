package com.android.example.mypopularmovies.controller;

import com.android.example.mypopularmovies.models.ApiResponse;
import com.android.example.mypopularmovies.models.ImdbApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.example.mypopularmovies.models.ImdbApi.API_KEY;

public class ImdbController {
    private static final String IMDB_BASE_URL = "http://api.themoviedb.org/";


    public void loadMovies(SortType type, Callback callback) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ImdbApi ImdbApi = retrofit.create(com.android.example.mypopularmovies.models.ImdbApi.class);
        Call<ApiResponse> call =
                type == SortType.POPULAR ? ImdbApi.loadPopularMovies(API_KEY)
                        : ImdbApi.loadBestRatedMovies(API_KEY);
        System.out.println(call.request().url());
        call.enqueue(callback);
    }


    public enum SortType {
        POPULAR, RATED
    }
}
