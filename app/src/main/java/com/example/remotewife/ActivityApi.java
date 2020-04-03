package com.example.remotewife;

import com.example.remotewife.pojo.Task;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ActivityApi {
    @GET("/api/activity")
    public Call<Task> getJobWithActivity();
}
