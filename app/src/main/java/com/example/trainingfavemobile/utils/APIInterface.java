package com.example.trainingfavemobile.utils;

import com.example.trainingfavemobile.models.GamesResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("games")
    Call<List<GamesResponseItem>> getJSON();
}
