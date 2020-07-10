package com.android.retrofitexampleii.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.retrofitexampleii.model.CommentsModel;
import com.android.retrofitexampleii.repository.DataRespositry;

import java.util.List;

public class CommentsViewModel extends ViewModel {

    private LiveData<List<CommentsModel>> commentsList;
    private MutableLiveData<Boolean> isLoading;

    public void init() {
        if (commentsList!=null){
            return;
        }
        DataRespositry dataRespositry = DataRespositry.getInstance();
        commentsList= dataRespositry.getComments();
        isLoading= dataRespositry.getIsLoading();
    }

    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }
    public LiveData<List<CommentsModel>> getData(){
        return commentsList;
    }
}
