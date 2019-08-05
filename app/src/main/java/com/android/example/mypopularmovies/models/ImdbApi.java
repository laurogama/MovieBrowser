package com.android.example.mypopularmovies.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImdbApi {
    String API_KEY = "<INSERT YOUR KEY>";
    String IMAGE_SIZE_W185 = "w185";
    String IMAGE_SIZE_ORIGINAL = "original";

    String IMAGE_URL = "http://image.tmdb.org/t/p/";

    String URL_MOVIE_POPULAR = "/3/movie/popular";
    String URL_MOVIE_BEST_RATED = "/3/movie/top_rated";
    String REQUEST_POSTER_FORMAT = "%s%s%s";

    @GET(URL_MOVIE_POPULAR)
    Call<ApiResponse> loadPopularMovies(@Query("api_key") String apiKey);

    @GET(URL_MOVIE_BEST_RATED)
    Call<ApiResponse> loadBestRatedMovies(@Query("api_key") String apiKey);
}
