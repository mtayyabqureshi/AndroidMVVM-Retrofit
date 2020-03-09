package com.android.retrofitexampleii.Interface;

import com.android.retrofitexampleii.DataModel.CommentsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommentsInterface {
    //https://jsonplaceholder.typicode.com/comments
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("comments")
    Call<List<CommentsModel>> getComments();

}
