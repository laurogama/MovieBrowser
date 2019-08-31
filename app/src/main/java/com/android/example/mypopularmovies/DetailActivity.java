package com.android.example.mypopularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.mypopularmovies.controller.ImdbController;
import com.android.example.mypopularmovies.models.ImdbApi;
import com.android.example.mypopularmovies.models.MovieModel;
import com.android.example.mypopularmovies.models.ReviewModel;
import com.android.example.mypopularmovies.models.ReviewResponse;
import com.android.example.mypopularmovies.models.TrailerModel;
import com.android.example.mypopularmovies.models.TrailerResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.example.mypopularmovies.utils.ImageUtils.buildPosterUrl;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "com.android.example.mypopularmovies.EXTRA_MOVIE";
    MovieModel movieModel;
    @BindView(R.id.tv_movie_title)
    TextView mTitle;
    @BindView(R.id.tv_release_date)
    TextView mReleaseDate;
    @BindView(R.id.iv_poster)
    ImageView mPosterThumbnail;
    @BindView(R.id.tv_vote_average)
    TextView mVoteAverage;
    @BindView(R.id.tv_plot_synopsis)
    TextView mOverview;
    @BindView(R.id.rv_trailers)
    RecyclerView mTrailerRecyclerView;
    @BindView(R.id.rv_reviews)
    RecyclerView mReviewRecyclerView;
    @BindView(R.id.tv_error_parseling)
    TextView mErrorLoading;
    private ImdbController mImdbController = new ImdbController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager reviewlayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView.LayoutManager trailerlayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mTrailerRecyclerView.setLayoutManager(trailerlayoutManager);
        mReviewRecyclerView.setLayoutManager(reviewlayoutManager);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MOVIE)) {

            movieModel = intent.getParcelableExtra(EXTRA_MOVIE);

            if (movieModel != null) {
                populateMovieDetails(movieModel);
                requestReviews(movieModel.getId());
                requestTrailers(movieModel.getId());
            }
        } else {
            mErrorLoading.setVisibility(View.VISIBLE);
        }
    }


    private void requestTrailers(Integer movieId) {
        mImdbController.loadTrailers(movieId, new Callback<TrailerResponse>() {

            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    TrailerResponse trailerResponse = response.body();
                    updateTrailerRecyclerView(trailerResponse.getResults());
                } else {
                    System.out.println(response.headers().toString());
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void requestReviews(Integer movieId) {
        mImdbController.loadReviews(movieId, new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful()) {

                    ReviewResponse reviewResponse = response.body();
                    updateReviewRecyclerView(reviewResponse.getResults());
                } else {
                    System.out.println(response.headers().toString());
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                showErrorRetrievingReviews();
                t.printStackTrace();
            }
        });
    }

    private void populateMovieDetails(MovieModel movieModel) {

        mTitle.setText(movieModel.getTitle());
        mVoteAverage.setText(String.valueOf(movieModel.getVote_average()));
        mReleaseDate.setText(String.format("(%s)", movieModel.getRelease_date().substring(0, 4)));
        mOverview.setText(movieModel.getOverview());
        Picasso.get()
                .load(
                        buildPosterUrl(movieModel.getPoster_path(), ImdbApi.IMAGE_SIZE_ORIGINAL)
                )
                .error(R.drawable.placeholder)
                .into(mPosterThumbnail);

    }


    private void updateReviewRecyclerView(List<ReviewModel> reviews) {
        //TODO set listAdapter for reviews
        mReviewRecyclerView.setAdapter(new ReviewAdapter(reviews, this::onReviewListItemClick));

    }

    private void updateTrailerRecyclerView(List<TrailerModel> trailerModelList) {
        mTrailerRecyclerView.setAdapter(new TrailerAdapter(trailerModelList, this::onTrailerListItemClick));
    }

    public void onTrailerListItemClick(TrailerModel clickedTrailer) {

    }

    public void onReviewListItemClick(ReviewModel clickedReview) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(DetailActivity.EXTRA_MOVIE, clickedMovie);
//        startActivity(intent);
    }


    private void showErrorRetrievingReviews() {

    }
}
