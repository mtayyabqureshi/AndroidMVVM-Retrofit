package com.android.retrofitexampleii.Repository;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.retrofitexampleii.DataModel.CommentsModel;
import com.android.retrofitexampleii.Interface.CommentsInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRespositry {

    private MutableLiveData<List<CommentsModel>> commentsList;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private static DataRespositry instance;



    public static DataRespositry getInstance() {
        if (instance == null) {
            instance = new DataRespositry();
        }
        return instance;
    }
    //we will call this method to get the data
    public LiveData<List<CommentsModel>> getComments() {
        //if the list is null
        if (commentsList == null) {
            commentsList = new MutableLiveData<List<CommentsModel>>();
            isLoading.postValue(false);
            getData();
        }
        return commentsList;
    }

    public void getData(){
        Log.wtf("info_","getData : ");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommentsInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommentsInterface api = retrofit.create(CommentsInterface.class);
        Call<List<CommentsModel>> call = api.getComments();
        call.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                Log.wtf("info","response : "+response.body());
                commentsList.setValue(response.body());
                isLoading.postValue(true);
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                Log.wtf("info","failure : "+t.getMessage());
                isLoading.postValue(false);
            }
        });
    }
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }


}
