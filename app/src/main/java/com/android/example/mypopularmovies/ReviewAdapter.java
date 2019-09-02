package com.android.example.mypopularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.mypopularmovies.models.ReviewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    final private ListItemClickListener mOnClickListener;
    private ArrayList<ReviewModel> mReviewList;

    public ReviewAdapter(List<ReviewModel> reviews, ListItemClickListener listener) {
        mReviewList = (ArrayList<ReviewModel>) reviews;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewModel review = mReviewList.get(position);
        holder.author.setText(String.format("Author: %s", review.getAuthor()));
        holder.content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        if (mReviewList != null) {
            return mReviewList.size();
        } else {
            return 0;
        }
    }


    public interface ListItemClickListener {
        void onListItemClick(ReviewModel clickedItem);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_review_author)
        TextView author;
        @BindView(R.id.tv_review_content)
        TextView content;

        ReviewViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(mReviewList.get(getAdapterPosition()));
        }
    }

}
