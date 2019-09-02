package com.android.example.mypopularmovies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.example.mypopularmovies.models.MovieModel;
import com.android.example.mypopularmovies.repository.Repository;

import java.util.List;

public class MovieBrowserViewModel extends AndroidViewModel {
    private final Repository mRepository;
    private final LiveData<List<MovieModel>> mAllFavorites;

    public MovieBrowserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllFavorites = mRepository.getAllFavorites();
    }

    LiveData<List<MovieModel>> getFavorites() {
        return mAllFavorites;
    }

    public void insert(MovieModel movie) {
        mRepository.insert(movie);

    }

    void updateFavorite(MovieModel movie) {
        if (!mRepository.contains(movie)) {
            movie.setFavorite(true);
            mRepository.insert(movie);
        } else {
            mRepository.delete(movie);

        }

    }

    boolean isFavorite(MovieModel movieModel) {
        return mRepository.contains(movieModel);
    }
}
