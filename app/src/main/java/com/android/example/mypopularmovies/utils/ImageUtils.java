package com.android.example.mypopularmovies.utils;

import static com.android.example.mypopularmovies.models.ImdbApi.IMAGE_URL;
import static com.android.example.mypopularmovies.models.ImdbApi.REQUEST_POSTER_FORMAT;

public class ImageUtils {
    public static String buildPosterUrl(String posterPath, String size) {
        return String.format(REQUEST_POSTER_FORMAT, IMAGE_URL, size, posterPath);
    }
}
