package com.android.example.mypopularmovies.models;

import java.util.List;

public class ApiResponse {
    public Integer getPage() {
        return page;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    private Integer page;
    private List<MovieModel> results = null;
    private Integer totalResults;
    private Integer totalPages;


}
