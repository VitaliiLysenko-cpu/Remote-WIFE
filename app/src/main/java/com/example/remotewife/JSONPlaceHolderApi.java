package com.example.remotewife;

import com.example.remotewife.polo.Job;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/api/activity")
    public Call<Job> getJobWithActivity();
}
