package com.android.example.mypopularmovies.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.example.mypopularmovies.models.MovieModel;

@Database(entities = {MovieModel.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movie_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao favoriteDao();
}
