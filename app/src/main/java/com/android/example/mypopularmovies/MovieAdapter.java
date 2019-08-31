package com.android.example.mypopularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.mypopularmovies.models.ImdbApi;
import com.android.example.mypopularmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.android.example.mypopularmovies.utils.ImageUtils.buildPosterUrl;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieModel> movies;
    private static final String TAG = "movieAdapter";
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(MovieModel clickedItem);
    }

    MovieAdapter(List<MovieModel> mMovies, ListItemClickListener listener) {
        this.movies = (ArrayList<MovieModel>) mMovies;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_poster, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Picasso.get()
                .load(
                        buildPosterUrl(movies.get(position).getPoster_path(), ImdbApi.IMAGE_SIZE_W185)
                )
                .error(R.drawable.placeholder)
                .into(holder.posterImageView);

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = (ArrayList<MovieModel>) movies;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        ImageView posterImageView;

        MovieViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.iv_list_item_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(movies.get(getAdapterPosition()));
        }
    }
}
