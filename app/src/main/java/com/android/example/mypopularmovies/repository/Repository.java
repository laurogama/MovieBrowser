package com.android.example.mypopularmovies.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.example.mypopularmovies.models.MovieModel;
import com.android.example.mypopularmovies.persistence.MovieDao;
import com.android.example.mypopularmovies.persistence.MovieDatabase;

import java.util.List;

public class Repository {
    private MovieDao mMovieDao;
    private LiveData<List<MovieModel>> mFavoriteMovies;

    public Repository(Application application) {
        MovieDatabase db = MovieDatabase.getDatabase(application.getApplicationContext());
        mMovieDao = db.favoriteDao();
        mFavoriteMovies = mMovieDao.getAll();
    }

    public LiveData<List<MovieModel>> getAllFavorites() {
        return mFavoriteMovies;
    }

    public void insert(MovieModel movie) {
        new insertAsyncTask(mMovieDao).execute(movie);
    }

    public boolean contains(MovieModel movie) {
        return mMovieDao.findById(movie.getId()) != null;
    }

    public void delete(MovieModel movie) {
        new deleteAsyncTask(mMovieDao).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<MovieModel, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<MovieModel, Void, Void> {
        private MovieDao mAsyncTaskDao;

        deleteAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MovieModel... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
