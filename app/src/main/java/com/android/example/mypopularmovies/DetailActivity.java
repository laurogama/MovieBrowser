package com.android.example.mypopularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.mypopularmovies.models.ImdbApi;
import com.android.example.mypopularmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import static com.android.example.mypopularmovies.utils.ImageUtils.buildPosterUrl;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "com.android.example.mypopularmovies.EXTRA_MOVIE";
    private MovieModel movieModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MOVIE)) {
            movieModel = intent.getParcelableExtra(EXTRA_MOVIE);

            if (movieModel != null) {
                populateMovieDetails(movieModel);
            }
        } else {
            TextView mErrorLoading = findViewById(R.id.tv_error_parseling);
            mErrorLoading.setVisibility(View.VISIBLE);
        }
    }

    private void populateMovieDetails(MovieModel movieModel) {
        TextView mTitle = findViewById(R.id.tv_movie_title);
        mTitle.setText(movieModel.getTitle());
        TextView mReleaseDate = findViewById(R.id.tv_release_date);
        ImageView mPosterThumbnail = findViewById(R.id.iv_poster);
        TextView mVoteAverage = findViewById(R.id.tv_vote_average);
        mVoteAverage.setText(String.valueOf(movieModel.getVote_average()));
        mReleaseDate.setText(String.format("(%s)", movieModel.getRelease_date().substring(0, 4)));
        TextView mOverview = findViewById(R.id.tv_plot_synopsis);
        mOverview.setText(movieModel.getOverview());
        Picasso.get()
                .load(
                        buildPosterUrl(movieModel.getPoster_path(), ImdbApi.IMAGE_SIZE_ORIGINAL)
                )
                .error(R.drawable.placeholder)
                .into(mPosterThumbnail);

    }
}
