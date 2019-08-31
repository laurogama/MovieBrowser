package com.android.example.mypopularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.mypopularmovies.models.TrailerModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    final private TrailerAdapter.ListItemClickListener mOnClickListener;
    private ArrayList<TrailerModel> mTrailerList;

    public TrailerAdapter(List<TrailerModel> trailers, ListItemClickListener listener) {
        mTrailerList = (ArrayList<TrailerModel>) trailers;
        mOnClickListener = listener;


    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.trailerName.setText(mTrailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTrailerList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(TrailerModel clickedItem);
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_trailer_name)
        TextView trailerName;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
