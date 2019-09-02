package com.android.example.mypopularmovies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.example.mypopularmovies.models.MovieModel;
import com.android.example.mypopularmovies.repository.Repository;

import java.util.List;

public class MovieBrowserViewModel extends AndroidViewModel {
    private Repository mRepository;
    private LiveData<List<MovieModel>> mAllFavorites;

    public MovieBrowserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllFavorites = mRepository.getAllFavorites();
    }

    public LiveData<List<MovieModel>> getFavorites() {
        return mAllFavorites;
    }

    public void insert(MovieModel movie) {
        mRepository.insert(movie);

    }

    public void updateFavorite(MovieModel movie) {
        if (!mRepository.contains(movie)) {
            movie.setFavorite(true);
            mRepository.insert(movie);
        } else {
            mRepository.delete(movie);

        }

    }

    public boolean isFavorite(MovieModel movieModel) {
        return mRepository.contains(movieModel);
    }
}
