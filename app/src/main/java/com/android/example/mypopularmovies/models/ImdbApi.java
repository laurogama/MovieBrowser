package com.android.example.mypopularmovies.models;

import com.android.example.mypopularmovies.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImdbApi {
    String API_KEY = BuildConfig.ApiKey;
    String IMAGE_SIZE_W185 = "w185";
    String IMAGE_SIZE_ORIGINAL = "original";

    String IMAGE_URL = "http://image.tmdb.org/t/p/";

    String URL_MOVIE_POPULAR = "/3/movie/popular";
    String URL_MOVIE_BEST_RATED = "/3/movie/top_rated";
    String REQUEST_POSTER_FORMAT = "%s%s%s";
    String URL_MOVIE_REVIEWS = "/3/movie/{id}/reviews";
    String URL_MOVIE_TRAILERS = "/3/movie/{id}/videos";

    @GET(URL_MOVIE_POPULAR)
    Call<MovieResponse> loadPopularMovies(@Query("api_key") String apiKey);

    @GET(URL_MOVIE_BEST_RATED)
    Call<MovieResponse> loadBestRatedMovies(@Query("api_key") String apiKey);

    @GET(URL_MOVIE_REVIEWS)
    Call<ReviewResponse> loadReviews(@Path("id") Integer id, @Query("api_key") String apiKey);

    @GET(URL_MOVIE_TRAILERS)
    Call<TrailerResponse> loadTrailers(@Path("id") Integer id, @Query("api_key") String apiKey);
}
