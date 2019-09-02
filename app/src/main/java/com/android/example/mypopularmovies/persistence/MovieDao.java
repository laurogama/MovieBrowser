package com.android.example.mypopularmovies.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.example.mypopularmovies.models.MovieModel;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<MovieModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieModel... movies);

    @Delete
    void delete(MovieModel movie);

    @Query("SELECT * FROM movies WHERE id=:id LIMIT 1")
    MovieModel findById(Integer id);
}
