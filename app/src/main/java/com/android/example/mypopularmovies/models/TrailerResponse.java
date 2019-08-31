package com.android.example.mypopularmovies.models;

import java.util.List;

public class TrailerResponse {
    private Integer id;
    private List<TrailerModel> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerModel> getResults() {
        return results;
    }

    public void setResults(List<TrailerModel> results) {
        this.results = results;
    }
}
