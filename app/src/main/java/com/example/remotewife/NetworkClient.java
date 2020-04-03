package com.example.remotewife;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private static NetworkClient instance;
    private static final String BASE_URL = "https://www.boredapi.com";
    private Retrofit retrofit;
    
    private NetworkClient() {
     retrofit = new Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build();
    }
    
    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }
    
    public ActivityApi getJSONApi(){
        return retrofit.create(ActivityApi.class);
    }
}
