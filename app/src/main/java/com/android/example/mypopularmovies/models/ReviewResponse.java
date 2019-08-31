package com.android.example.mypopularmovies.models;

import java.util.List;

public class ReviewResponse{
    private Integer page;
    private List<ReviewModel> results = null;
    private Integer totalResults;
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public List<ReviewModel> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }


}